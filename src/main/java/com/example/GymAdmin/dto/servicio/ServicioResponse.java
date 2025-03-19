package com.example.GymAdmin.dto.servicio;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class ServicioResponse {


    public Integer idServicio;

    private String nombreServicio;

    private String descripcion;

    private String estatus;

    private String createdAt;

    public ServicioResponse() {

    }
}
