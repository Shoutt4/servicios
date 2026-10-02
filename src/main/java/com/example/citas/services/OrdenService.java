package com.example.citas.services;

import org.springframework.stereotype.Service;

import com.example.citas.repository.OrdenRepository;
import com.example.citas.dto.orden.OrdenResponse;
import com.example.citas.models.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class OrdenService {
    private final OrdenRepository ordenRepository;

    public OrdenService(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    private static OrdenResponse convertirOrdenResponse(Orden orden) {

        return OrdenResponse.builder().idOrden(orden.getIdOrden()).descripcion(orden.getDescripcion())
                .price(orden.getPrice()).build();

    }

    public Page<OrdenResponse> getOrdenByDescripcionPageable(String descripcion, Pageable pageable) {
        Page<Orden> ordenPage = this.ordenRepository.findByDescripcionContainingIgnoreCase(descripcion, pageable);
        if (!ordenPage.isEmpty()) {
            return ordenPage.map((O) -> convertirOrdenResponse(O));
        } else {
            throw new RuntimeException("datos no encontras con esa descirpcion");
        }
    }
}
