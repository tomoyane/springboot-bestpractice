package com.bestpractice.api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JsonWebTokenService {

    public String generateJwt(Long userId, int period) {

        Key key = MacProvider.generateKey();
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime expirationDate = now.plusDays(period).atZone(ZoneId.systemDefault());

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(Date.from(expirationDate.toInstant()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
