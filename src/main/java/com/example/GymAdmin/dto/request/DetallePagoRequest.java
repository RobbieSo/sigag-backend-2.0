package com.example.GymAdmin.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetallePagoRequest {

    private Integer idDetallepago;

    private String item;

    private BigDecimal monto;

    private String descripcion;


}
