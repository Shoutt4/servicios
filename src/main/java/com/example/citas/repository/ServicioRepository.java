package com.example.citas.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.citas.models.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, String> {

    Optional<Servicio> findByNombre(String nombre);

    Page<Servicio> findAll(Pageable page);

    @Transactional
    @Modifying
    @Query("UPDATE Servicio s SET s.activo = :estado WHERE s.id_service=:id")
    int updateEstado(
            @Param("estado") boolean estado,
            @Param("id") String id);
}
