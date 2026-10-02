package com.example.citas.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtServicee {
    private static final String SECRET_KEY = "n+0EiahZ+v5RTGfzJFtDQuuNdnc8pakkSlkfLbzDqtI=";

    public String getName(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extractClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKeySig(), Jwts.SIG.HS256)
                .compact();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolven) {
        final Claims claims = getAllClaims(token);
        return claimsResolven.apply(claims);
    }

    public Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKeySig())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public SecretKey getKeySig() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validToken(String token, UserDetails userDetails) {
        String userName = getName(token);
        return (userName.equals(userDetails.getUsername()) && isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());

    }

    public Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
