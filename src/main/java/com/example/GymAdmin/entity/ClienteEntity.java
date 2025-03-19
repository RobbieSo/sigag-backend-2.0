package com.example.GymAdmin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name="tbl_cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClienteEntity  implements Serializable {


    @Id
    @Column(name="id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idCliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id_persona")
    private PersonaEntity persona;


}
