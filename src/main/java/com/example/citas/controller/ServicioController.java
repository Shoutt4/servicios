package com.example.citas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.citas.dto.ServicioRequest;
import com.example.citas.dto.ServicioResponse;
import com.example.citas.services.ServicioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/register/servicio")
    public ResponseEntity<ServicioResponse> createServicio(@Valid @RequestBody ServicioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioService.createServicio(request));
    }

}
