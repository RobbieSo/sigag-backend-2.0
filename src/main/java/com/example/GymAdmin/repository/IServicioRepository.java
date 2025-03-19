package com.example.GymAdmin.repository;


import com.example.GymAdmin.entity.ServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicioRepository extends JpaRepository<ServicioEntity, Integer> {

}
