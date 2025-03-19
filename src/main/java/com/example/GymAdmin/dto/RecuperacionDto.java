package com.example.GymAdmin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecuperacionDto {
    @NotBlank
    private String username;

    @NotBlank
    private String correo;

    private String code;
    private String passwordUpdate;
}
