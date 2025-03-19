package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_servicios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ServicioEntity {

    @Id
    @Column(name="id_servicio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idServicio;

    @Column(name="nombre_servicio")
    private String nombreServicio;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="estatus")
    private String estatus;

    @Column(name="created_at")
    private LocalDateTime createdAtd;


    @ManyToMany(mappedBy = "servicios")
    private Set<MembresiaEntity> membresias = new HashSet<>();


    @Override
    public String toString() {
        return "ServicioEntity{" +
                "idServicio=" + idServicio +
                ", nombreServicio='" + nombreServicio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estatus='" + estatus + '\'' +
                ", createdAtd=" + createdAtd +
                ", membresias=" + membresias +
                '}';
    }
}
