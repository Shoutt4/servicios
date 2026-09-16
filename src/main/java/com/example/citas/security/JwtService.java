package com.example.citas.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.citas.models.Role;
import com.example.citas.models.Usuario;

import io.jsonwebtoken.Jwts;
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
                .expiration(new Date(System.currentTimeMillis() + 1000 * 10 * 100))
                .signWith(secretKey)
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
