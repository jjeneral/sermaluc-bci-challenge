package com.sermaluc.sermalucbcichallenge.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Debe ingresar email")
    private String email;
    @NotBlank(message = "Debe ingresar password")
    private String password;

}
