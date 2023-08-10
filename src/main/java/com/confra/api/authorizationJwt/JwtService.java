package com.confra.api.authorizationJwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "jM+zON+rwRL8KV63UOB3+pJOEw8O2/SamvujhimQtWRhW1kqoq37fKu4QXcE5leKLUUk+7UmPSAIW30QIOg5ktkmCYg1s/wh0SNr7FV+sle40ql6H2xnR+NgaX+T15a61cBAgNK9Pgtly2fuy/XYwmUIB/Zur86Lo+V03z2FMxCtcbDJEovW2sWaEVoKXz5GOonvvFRvv73NGmO43uDRYgJx5Mbb2Q/8c5+ayyiDtYje+2hBOZFbwkxvQJocbERN9W9LwfBd+G+VbqBCFx9TybuGJQ272Z2STgNKvwDiQPLTqH2eAX6xKysawTMuHvpDLBhITKnUROWfYixjMT/MFX1VfZWZeBygDImnY0ykxoPFUsQe3wtweWmpa+j2rN/p9KySzu5/iZdcuLVz0iYu1OAnmpfiULYn3Q3pPaMG0VeApI+0hxFnVxB+kUAI4Y1WLUVCuLEESCPP9caAFYEG1sN1NBPZ50EwA9Q+oZiYn+83p53uey1GLFaLWKM2A5VSiUBJWADW8anqz5o8Ov5n9p/7YywdLiL2J5W7KJy7RvkF06igRIvpvWTlUc8DriO3ZuAstpM8x1Ch+CD3a6zlUN1HGw+Y6dM1fVJ890+9bC8mUTCGYgR3OmbSwU61e/99R/xIjqt2v2fiKyKVoUnCqlSSwIyFTa8e5A6dCQPIUUc=\n";

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateTokenWithoutClaims(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
