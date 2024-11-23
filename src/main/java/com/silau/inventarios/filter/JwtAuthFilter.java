package com.silau.inventarios.filter;

import com.silau.inventarios.model.AdministradorModel;
import com.silau.inventarios.repository.AdministradorRepository;
import com.silau.inventarios.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Si la solicitud es para /auth, no procesar el filtro
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Si no existe el header o no es un Bearer Token, continuar con el filtro
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = authHeader.substring(7);
        final String user = jwtService.extractUser(jwtToken);

        // Si el usuario ya está autenticado en el contexto de seguridad, no hacer nada
        if (user != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Verificar si el token está expirado
        if (jwtService.isTokenExpired(jwtToken)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
            return;
        }

        // Cargar los detalles del usuario
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user);
        final Optional<AdministradorModel> admin = administradorRepository.findByUsuario(userDetails.getUsername());

        if (admin.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User Not Found");
            return;
        }

        final boolean isTokenValid = jwtService.isTokenValid(jwtToken, admin.get());

        // Si el token no es válido, devolver error 403
        if (!isTokenValid) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Token");
            return;
        }

        // Crear el token de autenticación
        final var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // Continuar con el filtro
        filterChain.doFilter(request, response);
    }
}
