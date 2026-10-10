package com.example.citas.dto.servicio;

import java.time.LocalDateTime;

import com.example.citas.dto.ServicioResponse;
import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.models.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservaResponseBasicDTO {
    private String idReserva;
    private String idUsuario;
    private String idServicio;
    private LocalDateTime fechaReserva;
    private Estado estado;
    private LocalDateTime fechaCreacion;
}
