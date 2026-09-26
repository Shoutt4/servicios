package com.example.citas.dto;

import java.time.LocalDateTime;

import com.example.citas.models.Estado;

import jakarta.persistence.PrePersist;

public class ReservaResponseDTO {
    private String idServicio;
    private UserRegisterResponse user;
    private ServicioResponse servicio;
    private LocalDateTime fechaReserva;
    private Estado estado;
    private LocalDateTime fechaCreacion;

    public Estado getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public ServicioResponse getServicio() {
        return servicio;
    }

    public UserRegisterResponse getUser() {
        return user;
    }



}
