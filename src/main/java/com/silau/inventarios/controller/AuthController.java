package com.silau.inventarios.controller;

import com.silau.inventarios.responses.LoginRequest;
import com.silau.inventarios.responses.TokenResponse;
import com.silau.inventarios.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody final LoginRequest login){
        final TokenResponse token = authService.login(login);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        TokenResponse tokenResponse = authService.refreshToken(authHeader);
        return ResponseEntity.ok(tokenResponse);
    }


}
