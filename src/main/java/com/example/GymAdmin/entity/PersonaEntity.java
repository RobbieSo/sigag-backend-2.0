package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="tbl_persona")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonaEntity {


    @Id
    @Column(name="id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idPersona;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido_paterno")
    private String apellidoPaterno;

    @Column(name="apellido_materno")
    private String apellidoMaterno;

    @Column(name="fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name="correo")
    private String correo;

    @Column(name="telefono")
    private String telefono;


}
