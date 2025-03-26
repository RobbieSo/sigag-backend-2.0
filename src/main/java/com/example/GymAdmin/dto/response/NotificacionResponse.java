package com.example.GymAdmin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionResponse {
    private Integer idNotificacion;
    private Integer idCliente;
    private String asunto;
    private String mensaje;
    private String fechaEnvio;

}
