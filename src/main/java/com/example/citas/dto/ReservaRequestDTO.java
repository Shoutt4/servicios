package com.example.citas.dto;

import java.time.LocalDateTime;

import com.example.citas.models.Estado;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

public class ReservaRequestDTO {
    @NotBlank(message = "el id  del usuario no debe ser nulo")
    private String idUsuario;
    @NotBlank(message = "el id del servicio no debe ser nulo ")
    private String idServicio;
    @NotNull(message = "la fecha de reserva es requerida")
    @Future(message = "la fecha de reserva debe ser futura ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSS")
    private LocalDateTime fechaReserva;
    @NotNull(message = "es requerido el estado")
    private Estado estado;

    public ReservaRequestDTO(String idUsuario, String idServicio, Estado estado, LocalDateTime fechaReserva) {

        this.idUsuario = idUsuario;
        this.idServicio = idServicio;
        this.estado = estado;
        this.fechaReserva = fechaReserva;

    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public String getIdServicio() {
        return this.idServicio;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

}
