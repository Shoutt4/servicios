package com.example.citas.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final SecretKey secretKey = Keys
            .hmacShaKeyFor("CLAVE_SUPER_SECRETA_Y_SEGURA_PARA_JWT_123456789_SPRING_BOOT".getBytes());

    public String generateToken(String email, String rol) {

        return Jwts.builder()
                .subject(email)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();

    }

    public String generateToken(Map<String, Object> claims, String subJect) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subJect)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }

    public String getName(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
