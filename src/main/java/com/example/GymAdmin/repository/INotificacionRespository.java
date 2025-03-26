package com.example.GymAdmin.repository;


import com.example.GymAdmin.entity.NotificacionEntity;
import com.example.GymAdmin.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacionRespository extends JpaRepository<NotificacionEntity, Integer> {
}
