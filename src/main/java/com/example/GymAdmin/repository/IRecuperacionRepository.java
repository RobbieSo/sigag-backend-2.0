package com.example.GymAdmin.repository;

import com.example.GymAdmin.entity.RecuperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRecuperacionRepository extends JpaRepository<RecuperacionEntity, Integer> {
}
