package com.example.GymAdmin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tbl_contrato")
@NoArgsConstructor
@AllArgsConstructor

@Setter
@Getter
public class ContratoEntity {

    @Id
    @Column(name="id_contratos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idContrato;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @OneToOne
    @JoinColumn(name = "membresia_id", referencedColumnName = "id_membresia", nullable = false)
    private MembresiaEntity membresia;



    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagoEntity> pagos;

}
