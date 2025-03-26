package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="tbl_notificacion")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NotificacionEntity {

    @Id
    @Column(name="id_notificacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idnotificacion;

    @Column(name="asunto")
    private String asunto;

    @Column(name="mensaje")
    private String mensaje;

    @Column(name="fecha_envio")
    private LocalDate fechaEnvio;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity cliente;
}
