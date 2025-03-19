package com.example.GymAdmin.dto.response;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

@Builder
@Setter
@Getter
public class ClienteResponse {

    private Integer idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;

}
