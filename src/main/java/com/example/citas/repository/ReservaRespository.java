package com.example.citas.repository;

import com.example.citas.models.Estado;
import com.example.citas.models.Reserva;
import com.example.citas.models.Role;

import java.time.LocalDateTime;
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

        List<Reserva> findByUsuarioId_IdUser(String idUsuario);

        boolean existsByUsuarioId_IdUser(String id);

        boolean existsByServicio_IdServicio(String id);

        List<Reserva> findByServicio_IdServicioIsAndEstadoIs(String idServicio, Estado estado);

        List<Reserva> findByUsuarioId_IdUserIsOrderByFechaReservaAsc(String idUser);

        List<Reserva> findByFechaReservaBetween(LocalDateTime min, LocalDateTime max);

        int countByFechaReservaBetween(LocalDateTime min, LocalDateTime max);

        boolean existsByEstado(Estado estado);

        List<Reserva> findByEstado(Estado estado);

        int countByFechaReservaAndEstado(LocalDateTime fecha, Estado estado);

        List<Reserva> findByFechaReservaAndEstado(LocalDateTime fecha, Estado estado);
}
