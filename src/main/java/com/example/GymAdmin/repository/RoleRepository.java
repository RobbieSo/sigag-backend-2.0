package com.example.GymAdmin.repository;

import com.example.GymAdmin.entity.Role;
import com.example.GymAdmin.util.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
