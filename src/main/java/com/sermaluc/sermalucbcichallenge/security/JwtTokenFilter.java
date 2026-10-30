package com.sermaluc.sermalucbcichallenge.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {

            try {
                String token = getToken(request);

                if (jwtProvider.validateToken(token)) {

                    String username = jwtProvider.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (MalformedJwtException e) {
                log.error("Token mal formado");
                request.setAttribute("exception", "Token mal formado: " + e.getMessage());
            } catch (UnsupportedJwtException e) {
                log.error("Token no soportado");
                request.setAttribute("exception", "Token no soportado: " + e.getMessage());
            } catch (ExpiredJwtException e) {
                log.error("Token expirado");
                request.setAttribute("exception", "Token expirado: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                log.error("Token vacio");
                request.setAttribute("exception", "Token vacío: " + e.getMessage());
            } catch (SecurityException e) {
                log.error("Fallo con la firma");
                request.setAttribute("exception", "Fallo con la firma: " + e.getMessage());
            } catch (Exception e) {
                log.error("Error de autenticacion:  " + e.getMessage());
                request.setAttribute("exception", "Error de autenticacion: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    //Obtenemos el token sin Bearer + el espacio
    private String getToken(HttpServletRequest request) throws Exception {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        else {
            log.warn("JWT no comienza con cadena Bearer");
            throw new Exception("JWT no comienza con cadena Bearer");
        }
    }
}