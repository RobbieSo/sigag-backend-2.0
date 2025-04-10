package com.example.GymAdmin.dto.request;



import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Past(message = "La fecha debe ser anterior a la fecha actual")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;

}