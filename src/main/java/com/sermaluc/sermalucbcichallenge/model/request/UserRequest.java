package com.sermaluc.sermalucbcichallenge.model.request;

import com.sermaluc.sermalucbcichallenge.constraint.CorreoDisponible;
import com.sermaluc.sermalucbcichallenge.constraint.CreateGroup;
import com.sermaluc.sermalucbcichallenge.constraint.FormatoPassword;
import com.sermaluc.sermalucbcichallenge.model.dto.PhoneDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Debe ingresar name")
    private String name;
    @NotBlank(message = "Debe ingresar email")
    @Email(message = "Email no válido")
    @CorreoDisponible(groups = CreateGroup.class)
    private String email;
    @NotBlank(message = "Debe ingresar password")
    @FormatoPassword
    private String password;
    private Boolean isactive;
    private List<PhoneDto> phones;
}