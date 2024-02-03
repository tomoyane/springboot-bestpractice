package com.bestpractice.api.domain.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class AuthComponent {
  private final CredentialProperty credentialProperty;
  private final Algorithm algorithm;

  public AuthComponent(CredentialProperty credentialProperty) {
    this.credentialProperty = credentialProperty;
    this.algorithm = Algorithm.HMAC256(this.credentialProperty.getHmacSecret());
  }

  public DecodedJWT decodeJwt(String token) {
    try {
      return JWT.require(this.algorithm)
          .build()
          .verify(token);
    } catch (SignatureVerificationException ex) {
      throw new InternalServerError("Unknown signature secret key");
    } catch (TokenExpiredException ex) {
      throw new UnAuthorized("Token is expired time");
    } catch (MissingClaimException | IncorrectClaimException | JWTDecodeException ex) {
      throw new UnAuthorized("Invalid token");
    } catch (Exception ex) {
      throw new InternalServerError("Unexpected error occurred");
    }
  }

  public static final String ClaimUserIdKey = "user_id";
  public static final String ClaimUserEmailKey = "user_email";
  public static final String ClaimRefreshKey = "refresh_token";
  public Credential generateJwt(String userId, String email, boolean isRefresh) {
    Integer expiresHour = this.credentialProperty.convertToIntExpires();

    Map<String, Object> header = new HashMap<>();
    header.put("alg", this.algorithm.getName());
    header.put("typ", "JWT");

    JWTCreator.Builder builder = JWT.create()
        .withIssuer(this.credentialProperty.getProvider())
        .withAudience("any")
        .withIssuedAt(new Date())
        .withHeader(header)
        .withClaim(ClaimUserIdKey, userId)
        .withClaim(ClaimUserEmailKey, email)
        .withSubject(userId);

    Date exp = null;
    if (expiresHour != null && !isRefresh) {
      exp = getExpiration(expiresHour);
      builder = builder.withExpiresAt(exp);
    }
    if (isRefresh) {
      builder = builder.withClaim(ClaimRefreshKey, true);
    } else {
      builder = builder.withClaim(ClaimRefreshKey, false);
    }
    return new Credential(builder.sign(this.algorithm), "Bearer", exp, isRefresh);
  }

  private static Date getExpiration(int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR, hour);
    return calendar.getTime();
  }
}