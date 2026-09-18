package com.example.citas.services;

import org.hibernate.query.Page;
import org.springframework.stereotype.Service;
import com.example.citas.repository.ServicioRepository;
import com.example.citas.dto.ServicioRequest;
import com.example.citas.dto.ServicioResponse;
import com.example.citas.models.Servicio;

@Service
public class ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    private Servicio convertirRequest(ServicioRequest request) {
        return new Servicio(request.getNombre(), request.getDescipcion(), request.getDuracion_servicio(),
                request.getPrecio(), request.getActivo());
    }

    private ServicioResponse convertirServicio(Servicio servicio) {
        return new ServicioResponse(servicio.getId_service(), servicio.getNombre(), servicio.getDuracion_Service(),
                servicio.getActivo(), servicio.getPrecio());
    }

    public ServicioResponse createServicio(ServicioRequest request) {
        if (!this.servicioRepository.findByNombre(request.getNombre()).isPresent()) {
            return convertirServicio(this.servicioRepository.save(convertirRequest(request)));
        } else {
            throw new IllegalArgumentException("error al crear el servicio");
        }
    }
}
