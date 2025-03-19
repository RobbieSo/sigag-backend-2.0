package com.example.GymAdmin.dto.membresia;


import com.example.GymAdmin.dto.servicio.ServicioResponse;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembresiaResponse {

    private Integer idMembresia;

    private String tipo;
    private String plan;
    private String horario;
    private String condiciones;
    private Boolean entrenamientoPersonalizado;
    private String vigencia;
    private String fechainicio;
    private Integer descuento;
    private Boolean asesoria;
    private Boolean estatus;
    private List<ServicioResponse> servicios;

}
