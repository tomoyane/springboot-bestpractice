package com.bestpractice.api.service;

import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.CredentialModel;
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
                .setIssuer(credentialProperty.getProvider())
                .setSubject(String.valueOf(userId))
                .setAudience(credentialProperty.getSubject())
                .setId(uuid)
                .setExpiration(Date.from(expirationDate.toInstant()))
                .signWith(SignatureAlgorithm.HS512, credentialProperty.getKey().getBytes())
                .compact();
    }

    public boolean verifyJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(credentialProperty.getKey().getBytes()).parseClaimsJws(jwt);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CredentialModel decodeJwt(String jwt) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String[] jwtSections = jwt.split("\\.");
            String claim = new String(Base64.getDecoder().decode(jwtSections[1]));

            return mapper.<CredentialModel>readValue(claim, CredentialModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CredentialModel();
    }
}
