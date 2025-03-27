package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="tbl_asistencia")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AsistenciaEntity {

    @Id
    @Column(name="id_asistencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idAsistencia;

    @Column(name="fecha_hora_regsitro")
    private LocalDateTime fechaHoraRegistro;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity cliente;

}
