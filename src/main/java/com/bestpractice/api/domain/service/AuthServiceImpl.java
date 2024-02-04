package com.bestpractice.api.domain.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  private final BCryptPasswordEncryptionComponent encryptionComponent;
  private final AuthComponent authComponent;
  private final UserPersistentRepository userPersistentRepository;

  public AuthServiceImpl(BCryptPasswordEncryptionComponent encryptionComponent,
      AuthComponent authComponent,
      UserPersistentRepository userPersistentRepository) {

    this.encryptionComponent = encryptionComponent;
    this.authComponent = authComponent;
    this.userPersistentRepository = userPersistentRepository;
  }

  public AuthResponse login(String email, String password) {
    User user = this.userPersistentRepository.findByEmail(email);
    if (user == null) {
      throw new UnAuthorized("Email or password is invalid");
    }
    if (!this.encryptionComponent.matchedPassword(password, user.getPassword())) {
      throw new UnAuthorized("Email or password is invalid");
    }

    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential refreshToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    return new AuthResponse(token.getTokenType(), token.getToken(), refreshToken.getToken(), token.getExp());
  }

  public AuthResponse login(String refreshToken) {
    DecodedJWT decodedJWT = this.authComponent.decodeJwt(refreshToken);
    String email = decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString();
    boolean isRefresh = decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean();

    User user = this.userPersistentRepository.findByEmail(email);
    if (user == null || !isRefresh) {
      throw new UnAuthorized("Token invalid");
    }

    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential rToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    return new AuthResponse(token.getTokenType(), token.getToken(), rToken.getToken(), token.getExp());
  }
}
