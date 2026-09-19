package com.example.citas.services;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.server.ResponseStatusException;

import com.example.citas.repository.ServicioRepository;
import com.example.citas.dto.ServicioRequest;
import com.example.citas.dto.ServicioResponse;
import com.example.citas.excepcion.ServicioException;
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

    public Page<ServicioResponse> getAll(Pageable page) {
        Page<Servicio> servicio = this.servicioRepository.findAll(page);
        if (!servicio.isEmpty()) {
            return servicio.map(this::convertirServicio);
        } else {
            throw new ServicioException("el arreglo esta vacio no se encontraron datos");
        }
    }

    public ServicioResponse getDetaillsService(String id_service) {
        return convertirServicio(this.servicioRepository.findById(id_service)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "el servicio no existe")));
    }

    public Map<String, Object> actualizarServicio(String id, ServicioRequest request) {
        Map<String, Object> respuesta = new HashMap<>();
        if (this.servicioRepository.findById(id).isPresent()) {
            Servicio servicio = this.servicioRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "error id invalido"));
            servicio.setNombre(request.getNombre());
            servicio.setDescripcion(request.getDescipcion());
            servicio.setActivo(request.getActivo());
            servicio.setDuracion_Service(request.getDuracion_servicio());
            servicio.setPrecio(request.getPrecio());
            respuesta.put("estado", "servicio actualizado correcetamente");
            respuesta.put("servicio", convertirServicio(this.servicioRepository.save(servicio)));
            return respuesta;
        } else {
            throw new ServicioException("no existe servicio con " + id + "registrado");
        }
    }

    public Map<String, Object> cambiarEstado(String id, Boolean estado) {
        Map<String, Object> result = new HashMap<>();
        if (this.servicioRepository.findById(id).isPresent()) {
            result.put("estado", "estado actualizado correctamente por " + estado);
            result.put("numero de serevicios modificados", (this.servicioRepository.updateEstado(estado, id)));
            return result;
        } else {
            throw new ServicioException("error al cambiar  el estado");
        }
    }
}
