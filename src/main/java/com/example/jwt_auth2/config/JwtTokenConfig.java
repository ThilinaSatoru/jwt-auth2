package com.example.jwt_auth2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtTokenConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil(jwtSecret, jwtExpiration);
    }
}
