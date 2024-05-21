package com.mauricio.dev.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtUtil {


    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    private SecretKey getSignInKey() {
        byte[] ketBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(ketBytes);
    }

    public void validateToken(final String token) {
        Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token);
    }

}
