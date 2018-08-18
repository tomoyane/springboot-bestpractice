package com.bestpractice.api.domain.service;

import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

@Service
public class JsonWebTokenService {

    private final CredentialProperty credentialProperty;

    public JsonWebTokenService(CredentialProperty credentialProperty) {
        this.credentialProperty = credentialProperty;
    }

    public String generateJwt(Long userId, int period, String uuid) {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime expirationDate = now.plusDays(period).atZone(ZoneId.systemDefault());

        return Jwts.builder()
                .setIssuer(this.credentialProperty.getProvider())
                .setSubject(String.valueOf(userId))
                .setAudience(this.credentialProperty.getSubject())
                .setId(uuid)
                .setExpiration(Date.from(expirationDate.toInstant()))
                .signWith(SignatureAlgorithm.HS512, this.credentialProperty.getKey().getBytes())
                .compact();
    }

    public boolean verifyJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(this.credentialProperty.getKey().getBytes()).parseClaimsJws(jwt);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Credential decodeJwt(String jwt) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String[] jwtSections = jwt.split("\\.");
            String claim = new String(Base64.getDecoder().decode(jwtSections[1]));

            return mapper.<Credential>readValue(claim, Credential.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Credential();
    }
}
