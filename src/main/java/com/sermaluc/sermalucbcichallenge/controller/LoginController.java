package com.sermaluc.sermalucbcichallenge.controller;

import com.sermaluc.sermalucbcichallenge.model.dto.ErrorResponseDto;
import com.sermaluc.sermalucbcichallenge.model.request.LoginRequest;
import com.sermaluc.sermalucbcichallenge.model.response.LoginResponse;
import com.sermaluc.sermalucbcichallenge.service.AuthenticationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;
    private final ConversionService conversionService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Error de autenticación", content = {@Content(mediaType
                    = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class))})})
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return conversionService.convert(authenticationService.login(request), LoginResponse.class);
    }

}
