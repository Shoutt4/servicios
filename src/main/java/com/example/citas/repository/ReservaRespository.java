package com.example.citas.repository;

import com.example.citas.models.Estado;
import com.example.citas.models.Reserva;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservaRespository extends JpaRepository<Reserva, String> {
        Page<Reserva> findAll(Pageable page);

        @Query("SELECT R FROM Reserva R  WHERE R.usuarioId.idUser=:id_usuario")
        Page<Reserva> getAllFromY(
                        @Param("id_usuario") String id_usuario,
                        Pageable page);

}
