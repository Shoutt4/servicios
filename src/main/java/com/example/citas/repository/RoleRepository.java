package com.example.citas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.citas.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByNameIgnoreCase(String name);
}
