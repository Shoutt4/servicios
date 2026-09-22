package com.example.citas.repository;

import com.example.citas.models.Reserva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRespository extends JpaRepository<Reserva, String> {
    Page<Reserva> findAll(Pageable page);
}
