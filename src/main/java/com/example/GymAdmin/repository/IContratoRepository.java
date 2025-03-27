package com.example.GymAdmin.repository;


import com.example.GymAdmin.entity.ClienteEntity;
import com.example.GymAdmin.entity.ContratoEntity;
import com.example.GymAdmin.entity.MembresiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContratoRepository extends JpaRepository<ContratoEntity, Integer> {


    Optional<ContratoEntity> findByCliente(ClienteEntity cliente);


}
