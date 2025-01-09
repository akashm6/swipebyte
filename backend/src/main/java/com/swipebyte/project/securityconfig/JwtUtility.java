package com.swipebyte.project.securityconfig;

import java.security.Key;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.*;

@Component
public class JwtUtility {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationTime = 86400000;

    public String generateToken(String userId) {
        JwtBuilder builder = Jwts.builder();
        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + expirationTime);

        return builder.setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(userId)
                .signWith(secretKey)
                .compact();
    }

    public String validateToken(String token) {

        try {
            Jws<Claims> parser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            // returns the userId in the payload
            String userId = parser.getBody().getSubject();

            return userId;
        }

        catch (JwtException e) {

            return null;
        }
    }

}
