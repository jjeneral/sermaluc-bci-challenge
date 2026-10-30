package com.sermaluc.sermalucbcichallenge.controller;

import com.sermaluc.sermalucbcichallenge.model.dto.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationErrors(MethodArgumentNotValidException ex) {
        log.error("Error de validacion de entrada", ex);
        List<String> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return getErrorResponse(validationErrors);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNoDataFound(Exception ex) {
        log.error("Usuario no encontrado", ex);
        return getErrorResponse(Collections.singletonList("Usuario no encontrado"));
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDto handleSecurityErrors(Exception ex) {
        log.error("Error de autenticación", ex);
        return getErrorResponse(Collections.singletonList("Error de autenticacion"));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponseDto handleOtherExceptions(Exception ex) {
        log.error("Error general: ", ex);
        List<String> errors = Collections.singletonList(
                String.format(ex.getMessage() == null
                        ? ex.getClass().getCanonicalName() : ex.getMessage()));
        return getErrorResponse(errors);
    }

    private ErrorResponseDto getErrorResponse(List<String> errores) {
        return ErrorResponseDto.builder()
                .mensaje(String.join(",", errores))
                .build();
    }

}
