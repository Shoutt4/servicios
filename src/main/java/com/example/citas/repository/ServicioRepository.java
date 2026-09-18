package com.example.citas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.citas.models.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, String> {

    Optional<Servicio> findByNombre(String nombre);
}
