package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="tbl_recuperacion")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecuperacionEntity {
    @Id
    @Column(name="id_recuperacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idRecuperacion;

    @Column(name="code")
    private String code;

    @Column(name="vigencia")
    private LocalDateTime vigencia;

    @Column(name="estatus")
    private Boolean estatus;
}
