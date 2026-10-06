package com.example.citas.dto.auth;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.citas.dto.RoleResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String id_user;
    private String name;
    private String email;
    private int phone;
    private RoleResponse role;
    private LocalDateTime createAt;
}
