package com.example.citas.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import com.example.citas.models.Estado;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.citas.dto.CreateBookingDTO;
import com.example.citas.dto.ResponseBookingDTO;
import com.example.citas.services.ReservaService;
import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {

        this.reservaService = reservaService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> registrarReserva(@Valid @RequestBody CreateBookingDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.reservaService.registrarReserva(request));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getAll")
    public ResponseEntity<Page<ResponseBookingDTO>> getAll(Pageable page) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.reservaService.getAll(page));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/bookings/my-bookings")
    public ResponseEntity<Page<ResponseBookingDTO>> getFromy(@RequestParam(required = true) String id_user,
            Pageable page) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.reservaService.getAllFromy(page, id_user));
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/bookings/{id}/status")
    public ResponseEntity<Map<String, Object>> updateEstado(@PathVariable String id,
            @RequestParam Estado estado) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.reservaService.updateEstado(id, estado));
    }
}
