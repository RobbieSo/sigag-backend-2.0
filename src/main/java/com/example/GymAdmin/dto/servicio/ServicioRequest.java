package com.example.GymAdmin.dto.servicio;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class ServicioRequest {

    private Integer idServicio;


    private String nombreServicio;


    private String descripcion;


    private String estatus;
}
