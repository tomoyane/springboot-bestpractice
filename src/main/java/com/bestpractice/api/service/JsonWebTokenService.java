package com.bestpractice.api.service;

import com.bestpractice.api.common.property.AppProperty;
import com.bestpractice.api.domain.model.CredentialModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

@Service
public class JsonWebTokenService {

    private final AppProperty appProperty;

    public JsonWebTokenService(AppProperty appProperty) {
        this.appProperty = appProperty;
    }

    public Key generateSignature() {
        return MacProvider.generateKey();
    }

    public String generateJwt(Key key, Long userId, int period, String uuid) {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime expirationDate = now.plusDays(period).atZone(ZoneId.systemDefault());

        return Jwts.builder()
                .setIssuer(appProperty.getProvider())
                .setSubject(String.valueOf(userId))
                .setAudience(appProperty.getSubject())
                .setId(uuid)
                .setExpiration(Date.from(expirationDate.toInstant()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public CredentialModel decodeJwt(String jwt) {
        try {
            String[] jwtSections = jwt.split("\\.");
            String claim = new String(Base64.getDecoder().decode(jwtSections[1]));
            ObjectMapper mapper = new ObjectMapper();

            return mapper.<CredentialModel>readValue(claim, CredentialModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new CredentialModel();
        }
    }
}
