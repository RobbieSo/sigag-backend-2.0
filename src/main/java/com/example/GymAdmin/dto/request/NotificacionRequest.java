package com.example.GymAdmin.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionRequest {
    private Integer idCliente;
    private String asunto;
    private String mensaje;
}
