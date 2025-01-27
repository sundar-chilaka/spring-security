package com.security.SpringSecurity_Login_Register.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {


    
    private SecretKey secretKey =Keys.hmacShaKeyFor("odifwenkasfmciwrebfouqwnjeasfweijhfmwwkejfasnm".getBytes());

    @Value("${jwt.expiration}")
    private long expirationTime;

    // Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime)) // 1 hour expiration
                .signWith(secretKey)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;  // Token is invalid
        }
    }

    // Parse JWT Token and get Claims
    private Claims parseClaims(String token) {
        return Jwts.parser()
        		.verifyWith(secretKey)
        		.build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extract username from JWT Token
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }
}
