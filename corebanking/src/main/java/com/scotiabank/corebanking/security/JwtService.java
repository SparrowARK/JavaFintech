package com.scotiabank.corebanking.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "VGhpcy1pcy1hLXN1cGVyLXNlY3JldC1rZXktZm9yLWNvcmUtYmFua2luZy1hcHA=";

    private SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey())
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
