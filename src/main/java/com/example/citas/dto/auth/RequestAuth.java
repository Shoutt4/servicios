package com.example.citas.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestAuth {
    @NotBlank(message = "el nombre es requerido")
    @Size(min = 8, max = 50, message = "ingresa un texto minimo que contenga 8 caracteres , 50 caracteres nax")
    private String name;
    @Email(message = "el texto debe tener formato usuario@example.com")
    private String email;
}
