package com.example.GymAdmin.dto.membresia;

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
public class MembresiaRequest {
    private Integer idMembresia;

    private String plan;
    private String horario;
    private String condiciones;
    private Boolean entrenamientoPersonalizado;
    private LocalDateTime vigencia;
    private LocalDateTime fechainicio;
    private Integer descuento;
    private Boolean asesoria;
    private Boolean estatus;
    private String tipo;
    private List<Integer> idsServicios;
}
