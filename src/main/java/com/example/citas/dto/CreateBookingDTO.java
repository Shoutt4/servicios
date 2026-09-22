package com.example.citas.dto;

import com.example.citas.models.Estado;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookingDTO {
    @NotNull(message = "el estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @NotBlank(message = "el id_servicio es obligatorio")
    private String servici_id;
    @NotBlank(message = "el id id_usuario es obligatorio")
    private String usuario_id;

    public CreateBookingDTO(Estado estado, String servicio_id, String usuario_id) {
        this.estado = estado;
        this.servici_id = servicio_id;
        this.usuario_id = usuario_id;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getServici_id() {
        return servici_id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

}
