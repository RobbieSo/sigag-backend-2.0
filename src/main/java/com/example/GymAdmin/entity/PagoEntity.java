package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tbl_pagos")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PagoEntity {

    @Id
    @Column(name="id_pago")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idPago;

    @Column(name="monto")
    private BigDecimal monto;

    @Column(name="fecha_pago_efectuado")
    private LocalDate fechaPagoEfectuado;

    @Column(name="metodo_pago")
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "contrato_id", referencedColumnName = "id_contratos", nullable = false)
    private ContratoEntity contrato;

    @OneToMany(mappedBy = "pago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePagoEntity> detallePagos;
}
