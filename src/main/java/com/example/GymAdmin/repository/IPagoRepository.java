package com.example.GymAdmin.repository;


import com.example.GymAdmin.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoRepository extends JpaRepository<PagoEntity, Integer> {
}
