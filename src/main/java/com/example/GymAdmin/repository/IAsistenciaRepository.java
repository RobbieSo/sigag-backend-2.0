package com.example.GymAdmin.repository;

import com.example.GymAdmin.entity.AsistenciaEntity;
import com.example.GymAdmin.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IAsistenciaRepository extends JpaRepository<AsistenciaEntity, Integer> {


}
