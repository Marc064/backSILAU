package com.silau.inventarios.service;

import com.silau.inventarios.model.AdministradorModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.security.jwt.secret-key}")
    private String secretKey;

    @Value("${app.secutiry.jwt.expiration}")
    private long jwtExpiration;

    @Value("${app.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String extractUser(String token) {
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return jwtToken.getSubject();
    }

    public Date extractExpiration(String token) {
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return jwtToken.getExpiration();
    }

    public boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(final String token, final AdministradorModel admin) {
        final String user = extractUser(token);
        return (user.equals(admin.getNombre()) && !isTokenExpired(token));
    }

    public String generateToken(final AdministradorModel administrador) {
        return buildToken(administrador, jwtExpiration);
    }

    public String generateRefreshToken(final AdministradorModel administrador) {
        return buildToken(administrador, refreshExpiration);
    }

    public String buildToken(final AdministradorModel administrador, final long expiration) {
        return Jwts.builder()
                .id(String.valueOf(administrador.getIdAdministrador()))
                .claims(Map.of("nombre", administrador.getNombre()))
                .subject(administrador.getUsuario())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
