package com.example.citas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.citas.repository.OrdenRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.citas.dto.orden.OrdenResponse;
import org.springframework.data.domain.Page;
import com.example.citas.services.OrdenService;

@RestController
@RequestMapping("/api/orden")
public class OrdenController {

    private final OrdenService ordenRepository;

    public OrdenController(OrdenService ordenService) {
        this.ordenRepository = ordenService;
        ;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/byDescripcion")
    public ResponseEntity<Page<OrdenResponse>> getOrdenByDescripcion(@Valid @RequestParam String descripcion,
            Pageable page) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.ordenRepository.getOrdenByDescripcionPageable(descripcion, page));
    }
}
