package com.example.GymAdmin.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratoRequest {

    private Integer idContrato;
    @NotBlank
    private Integer idCliente;
    @NotBlank
    private Integer idMembresia;


}
