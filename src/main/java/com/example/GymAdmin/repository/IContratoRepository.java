package com.example.GymAdmin.repository;


import com.example.GymAdmin.entity.ContratoEntity;
import com.example.GymAdmin.entity.MembresiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContratoRepository extends JpaRepository<ContratoEntity, Integer> {
}
