package com.mauricio.dev.service;

import com.mauricio.dev.model.Token;
import com.mauricio.dev.model.User;
import com.mauricio.dev.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {


    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${application.security.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Autowired
    private TokenRepository tokenRepository;



    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateAccessToken(User user) {
        return generateToken(user,accessTokenExpiration);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user,refreshTokenExpiration);
    }

    private String generateToken(User user, long expireTime) {
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSignInKey())
                .compact();

        return token;
    }


    private SecretKey getSignInKey() {
        byte[] ketBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(ketBytes);
    }


    // Validations
    public boolean isValid(String token, UserDetails user) {
        String username = extractUserName(token);

        Token tok = tokenRepository
                .findByAccessToken(token).orElse(null);
        boolean isValidToken = tokenRepository
                .findByAccessToken(token)
                .map(t -> !t.isLoggedOut()).orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && isValidToken;
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isValidRefreshToken(String token, User user) {

        String username = extractUserName(token);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) ;
    }

    public void validateToken(final String token) {
        Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token);
    }


    // Extract Information
    public <T> T extractClaim(String token, Function<Claims,T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }


}
