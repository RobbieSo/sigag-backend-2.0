package com.example.GymAdmin.dto.request;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AsistenciaResponseCr {

    private Integer idAsistencia;
    private String fechaHoraRegistro;

}
