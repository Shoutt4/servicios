package com.example.citas.repository;

import java.util.List;
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
    @Query("UPDATE Servicio s SET s.activo = :estado WHERE s.idServicio=:id")
    int updateEstado(
            @Param("estado") boolean estado,
            @Param("id") String id);

    List<Servicio> findByActivo(Boolean activo);

    boolean existsByActivo(Boolean estado);

    List<Servicio> findByPrecioLessThan(double precio);

    boolean existsByPrecio(double precio);

    int countByPrecioLessThan(double precio);

    int countByPrecioBetween(double min, double max);

    List<Servicio> findByPrecioBetweenOrderByPrecioDesc(double min, double max);

    List<Servicio> findByNombreContainingAndActivo(String nombre, boolean activo);

    int countByNombreContainingAndActivo(String nombre, boolean activo);
}
