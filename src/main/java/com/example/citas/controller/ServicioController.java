package com.example.citas.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.citas.dto.ServicioRequest;
import com.example.citas.dto.ServicioResponse;
import com.example.citas.services.ServicioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/servicio")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<ServicioResponse> createServicio(@Valid @RequestBody ServicioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioService.createServicio(request));
    }

    @PreAuthorize("hasRole('USER')")

    @GetMapping("/getAll-pagination")
    public ResponseEntity<Page<ServicioResponse>> getAll(Pageable page) {
        return ResponseEntity.ok(this.servicioService.getAll(page));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/detaills/{id}")
    public ResponseEntity<ServicioResponse> getDetaills(@PathVariable String id) {
        return ResponseEntity.ok(this.servicioService.getDetaillsService(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateServicio(@PathVariable String id,
            @RequestBody ServicioRequest request) {
        return ResponseEntity.ok(this.servicioService.actualizarServicio(id, request));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{id}/activo")
    public ResponseEntity<Map<String, Object>> acutlizarEstado(
            @PathVariable String id,
            @RequestParam(required = true) Boolean estado) {
        return ResponseEntity.ok(this.servicioService.cambiarEstado(id, estado));
    }
}
