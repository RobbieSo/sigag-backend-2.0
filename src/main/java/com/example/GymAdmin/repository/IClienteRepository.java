package com.example.GymAdmin.repository;

import com.example.GymAdmin.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer> {
}
