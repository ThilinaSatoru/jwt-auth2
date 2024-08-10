package com.example.jwt_auth2.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

public class JwtTokenUtil {

    private final SecretKey secretKey;
    private final long expirationTimeInMillis;

    public JwtTokenUtil(String secretKeyBase64, long expirationTimeInMillis) {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyBase64));
        this.expirationTimeInMillis = expirationTimeInMillis;
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeInMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // Get username from token
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Check if token has expired
    public boolean isTokenExpired(String token) {
        Date expirationDate = getClaimsFromToken(token).getExpiration();
        return expirationDate.before(new Date());
    }

    // Validate the token
    public boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    // Extract claims from token
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

