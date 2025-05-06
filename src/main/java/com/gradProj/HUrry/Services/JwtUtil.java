package com.gradProj.HUrry.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // Token validity (e.g., 10 hours)
    private static final long TOKEN_VALIDITY = 10 * 60 * 60 * 1000;

    public String generateToken(String email, String role) {
        // Claims added to JWT payload
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        // Generate the token
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email) // Sub field is the unique user identifier
                .setIssuedAt(new Date(System.currentTimeMillis())) // Current time
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) // Token expiration
                .signWith(SignatureAlgorithm.HS256,secretKey) // Signing with the secret key
                .compact();
    }
}