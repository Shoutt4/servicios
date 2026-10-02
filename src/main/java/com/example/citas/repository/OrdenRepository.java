package com.example.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.citas.models.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, String> {

    Page<Orden> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);
}
