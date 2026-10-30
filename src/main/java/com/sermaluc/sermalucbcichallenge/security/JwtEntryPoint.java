package com.sermaluc.sermalucbcichallenge.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * EntryPoint requerido para que manejo de excepciones de Spring Security se pueda realizar con @ExceptionHandler
 * @see com.sermaluc.sermalucbcichallenge.controller.ErrorController#handleSecurityErrors(Exception)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtEntryPoint implements AuthenticationEntryPoint {

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) {

        handlerExceptionResolver.resolveException(request, response, null, authException);
    }
}