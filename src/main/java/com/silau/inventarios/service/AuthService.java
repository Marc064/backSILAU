package com.silau.inventarios.service;

import com.silau.inventarios.model.AdministradorModel;
import com.silau.inventarios.repository.AdministradorRepository;
import com.silau.inventarios.responses.LoginRequest;
import com.silau.inventarios.responses.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdministradorRepository administradorRepository;

    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.user(),
                        request.password()
                )
        );

        AdministradorModel admin = administradorRepository.findByUsuario(request.user())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var jwtToken = jwtService.generateToken(admin);
        var jwtRefreshToken = jwtService.generateRefreshToken(admin);

        return new TokenResponse(jwtToken, jwtRefreshToken);
    }

    public TokenResponse refreshToken(final String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token invalido");
        }

        final String refreshToken = authHeader.substring(7);
        final String user = jwtService.extractUser(refreshToken);

        if (user == null){
            throw new IllegalArgumentException("Token invalido");
        }

        final AdministradorModel admin = administradorRepository.findByUsuario(user)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!jwtService.isTokenValid(refreshToken, admin)){
            throw new IllegalArgumentException("Token invalido");
        }

        final String accessToken = jwtService.generateToken(admin);

        return new TokenResponse(accessToken, refreshToken);
    }

}
