package com.example.GymAdmin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePagoResponse {

    private Integer idDetallepago;

    private String item;

    private BigDecimal monto;

    private String descripcion;

}
