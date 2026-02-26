package com.example.Service.jwtservcei;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    private final SecretKey secretKey;
    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7; // 7 days

    public JwtTokenService() {
        this.secretKey = generateStrongSecretKey();
    }

    /**
     * Generates a strong 256-bit secret key once when the service is created.
     * This key remains the same for the entire application lifetime.
     */
    private SecretKey generateStrongSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256); // 256 bits = strong enough for HS256
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate secure JWT signing key", e);
        }
    }

    /**
     * Simplest token generation method - accepts only username
     */
    public String generateToken(String username) {
        return generateToken(new HashMap<>(), username);
    }

    /**
     * Full version - allows adding extra claims if needed later
     */
    private String generateToken(Map<String, Object> extraClaims, String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(secretKey)                    // modern, safe way
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Optional helper methods you might find useful
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date getExpirationFromToken(String token) {
        return extractExpiration(token);
    }
}