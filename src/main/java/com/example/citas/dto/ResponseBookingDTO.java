package com.example.citas.dto;

import java.time.LocalDateTime;

import com.example.citas.models.Estado;

public class ResponseBookingDTO {

    private String id;
    private LocalDateTime estado;
    private LocalDateTime fecha_reserva;
    private ServicioResponse servicioResponse;
    private UserRegisterResponse userRegisterResponse;

    public ResponseBookingDTO(String id, LocalDateTime estado, LocalDateTime fecha_reserva,
            ServicioResponse servicioResponse,
            UserRegisterResponse userRegisterResponse) {

        this.id = id;
        this.estado = estado;
        this.fecha_reserva = fecha_reserva;
        this.servicioResponse = servicioResponse;
        this.userRegisterResponse = userRegisterResponse;
    }

    public LocalDateTime getEstado() {
        return estado;
    }

    public LocalDateTime getFecha_reserva() {
        return fecha_reserva;
    }

    public String getId() {
        return id;
    }

    public ServicioResponse getServicioResponse() {
        return servicioResponse;
    }

    public UserRegisterResponse getUserRegisterResponse() {
        return userRegisterResponse;
    }

}
