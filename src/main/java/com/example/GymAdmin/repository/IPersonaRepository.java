package com.example.GymAdmin.repository;


import com.example.GymAdmin.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaEntity, Integer> {
}
