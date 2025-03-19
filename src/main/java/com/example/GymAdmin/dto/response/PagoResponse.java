package com.example.GymAdmin.dto.response;


import com.example.GymAdmin.dto.request.DetallePagoRequest;
import com.example.GymAdmin.dto.servicio.ServicioResponse;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoResponse {

    private Integer idPago;
    private Integer idContrato;
    private BigDecimal monto;
    private String metodoPago;

    private String fechaPagoEfectuado;
    private List<DetallePagoResponse> detallePagos;


}
