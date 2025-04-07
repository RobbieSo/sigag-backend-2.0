package com.example.GymAdmin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$",
            message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula y un carácter especial"
    )
    private String password;

    @NotBlank
    private String telefono;

    @NotBlank
    private String correo;

    @NotBlank
    private String role;
}