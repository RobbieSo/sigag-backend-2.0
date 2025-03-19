package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="tbl_detalle_pago")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DetallePagoEntity {

    @Id
    @Column(name="id_detalle_pago")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idDetallePago;

    @Column(name="item")
    private String item;

    @Column(name="monto")
    private BigDecimal monto;

    @Column(name="descripcion")
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "pago_id", referencedColumnName = "id_pago", nullable = false)
    private PagoEntity pago;
}
