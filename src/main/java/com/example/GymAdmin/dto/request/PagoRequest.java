package com.example.GymAdmin.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class PagoRequest {

    private Integer idPago;
    @NotBlank
    private Integer idContrato;

    @NotBlank
    private BigDecimal monto;

    @NotBlank
    private String metodoPago;

    private List<DetallePagoRequest> detallePagos;

}
