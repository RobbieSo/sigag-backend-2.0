package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tbl_membresia")
@NoArgsConstructor
@AllArgsConstructor

@Setter
@Getter
public class MembresiaEntity {

    @Id
    @Column(name="id_membresia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idMembresia;

    @Column(name="tipo")
    private String tipo;

    @Column(name="plan")
    private String plan;

    @Column(name="horario")
    private String horario;

    @Column(name="condiciones")
    private String condiciones;

    @Column(name="entrenamiento_perosonalizado")
    private Boolean entrenamientoPersonalizado;

    @Column(name="vigencia")
    private LocalDateTime vigencia;

    @Column(name="fecha_inicio")
    private LocalDateTime fechainicio;

    @Column(name="descuento")
    private Integer descuento;

    @Column(name="asesoria_extra")
    private Boolean asesoria;

    @Column(name="estatus")
    private Boolean estatus;

    // Relación many-to-many: una membresía puede tener muchos servicios y viceversa
    @ManyToMany
    @JoinTable(
            name = "membresia_servicios",
            joinColumns = @JoinColumn(name = "id_membresia"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private Set<ServicioEntity> servicios = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembresiaEntity that)) return false;
        return Objects.equals(idMembresia, that.idMembresia) && Objects.equals(tipo, that.tipo) && Objects.equals(plan, that.plan) && Objects.equals(horario, that.horario) && Objects.equals(condiciones, that.condiciones) && Objects.equals(entrenamientoPersonalizado, that.entrenamientoPersonalizado) && Objects.equals(vigencia, that.vigencia) && Objects.equals(fechainicio, that.fechainicio) && Objects.equals(descuento, that.descuento) && Objects.equals(asesoria, that.asesoria) && Objects.equals(estatus, that.estatus) && Objects.equals(servicios, that.servicios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMembresia, tipo, plan, horario, condiciones, entrenamientoPersonalizado, vigencia, fechainicio, descuento, asesoria, estatus, servicios);
    }
}
