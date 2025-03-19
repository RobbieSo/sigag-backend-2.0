package com.example.GymAdmin.dto.request;


import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class ClienteRequest {

    private Integer idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;

}