package com.example.citas.dto;

import com.example.citas.models.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDtoReserva {
    private String nombreServicio;
    private Boolean activo;
    private double min;
    private double max;
    private Estado estado;
}
