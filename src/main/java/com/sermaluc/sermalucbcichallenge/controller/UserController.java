package com.sermaluc.sermalucbcichallenge.controller;


import com.sermaluc.sermalucbcichallenge.constraint.CreateGroup;
import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.request.UserRequest;
import com.sermaluc.sermalucbcichallenge.model.response.UserResponse;
import com.sermaluc.sermalucbcichallenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ConversionService conversionService;

    @Operation(summary = "Muestra un listado de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios registrados",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))
                    })
    })
    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll().stream()
                .map(user -> conversionService.convert(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtiene un usuario mediante su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)})
    @GetMapping("/{userId}")
    public UserResponse findById(@Parameter(description = "UUID del usuario a buscar") @PathVariable UUID userId) {
        return conversionService.convert(userService.getUserById(userId), UserResponse.class);
    }

    @Operation(summary = "Registra un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Validated({CreateGroup.class, Default.class}) @RequestBody UserRequest userRequest) {
        return conversionService.convert(
                userService.createUser(
                        conversionService.convert(userRequest, User.class)
                ), UserResponse.class);
    }

    @Operation(summary = "Actualiza un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @PutMapping("/{userId}")
    public UserResponse update(@Valid @RequestBody UserRequest userRequest,
                               @Parameter(description = "UUID del usuario a actualizar") @PathVariable UUID userId) {
        return conversionService.convert(
                userService.updateUser(
                        conversionService.convert(userRequest, User.class), userId
                ), UserResponse.class);
    }

}
