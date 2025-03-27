package com.example.GymAdmin.dto.response;


import com.example.GymAdmin.dto.membresia.MembresiaResponse;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AsistenciaResponse {

    private String mensaje;
    private String estado;
}
