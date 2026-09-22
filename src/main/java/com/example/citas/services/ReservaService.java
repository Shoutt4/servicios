package com.example.citas.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.citas.dto.CreateBookingDTO;
import com.example.citas.dto.ResponseBookingDTO;
import com.example.citas.dto.RoleResponse;
import com.example.citas.dto.ServicioResponse;
import com.example.citas.dto.UserRegisterResponse;
import com.example.citas.excepcion.AuthException;
import com.example.citas.excepcion.ServicioException;
import com.example.citas.models.Reserva;
import com.example.citas.models.Servicio;
import com.example.citas.models.Usuario;
import com.example.citas.repository.ReservaRespository;
import com.example.citas.repository.ServicioRepository;
import com.example.citas.repository.UsuarioRepository;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ReservaService {
        private final ReservaRespository reservaRespository;
        private final UsuarioRepository usuarioRepository;
        private final ServicioRepository servicioRepository;

        public ReservaService(ReservaRespository reservaRespository, ServicioRepository servicioRepository,
                        UsuarioRepository usuarioRepository) {
                this.reservaRespository = reservaRespository;
                this.servicioRepository = servicioRepository;
                this.usuarioRepository = usuarioRepository;
        }

        @Transactional
        private Reserva convertirRequest(CreateBookingDTO request) {
                Usuario user = this.usuarioRepository.findById(request.getUsuario_id())
                                .orElseThrow(() -> new AuthException(
                                                "usuario no encontrado con id  " + request.getUsuario_id()));
                Servicio servicio = this.servicioRepository.findById(request.getServici_id())
                                .orElseThrow(() -> new ServicioException(
                                                "servicio no encontrado con id " + request.getServici_id()));
                return new Reserva(user, servicio, request.getEstado());
        }

        private ResponseBookingDTO convertirReservaResponse(Reserva reserva) {
                ServicioResponse servicioResponse = new ServicioResponse(reserva.getServicio_id().getId_service(),
                                reserva.getServicio_id().getNombre(), reserva.getServicio_id().getDuracion_Service(),
                                reserva.getServicio_id().getActivo(), reserva.getServicio_id().getPrecio());
                UserRegisterResponse usuarioResponse = new UserRegisterResponse(reserva.getUsuario_id().getId(),
                                reserva.getUsuario_id().getName(), reserva.getUsuario_id().getEmail(),
                                reserva.getUsuario_id().getPhone(),
                                new RoleResponse(reserva.getUsuario_id().getRole().getId(),
                                                reserva.getUsuario_id().getRole().getName()));
                return new ResponseBookingDTO(reserva.getId_reserva(), reserva.getFecha_reserva(),
                                reserva.getFecha_reserva(),
                                servicioResponse, usuarioResponse);
        }

        @Transactional
        public Map<String, Object> registrarReserva(CreateBookingDTO request) {
                Reserva reserva = this.reservaRespository.save(convertirRequest(request));
                Map<String, Object> response = new HashMap<>();
                response.put("estado", "la reserva fue creada exitosamente");
                response.put("data", convertirReservaResponse(this.reservaRespository.save(reserva)));
                return response;

        }

        public Page<ResponseBookingDTO> getAll(Pageable page) {
                return this.reservaRespository.findAll(page).map(this::convertirReservaResponse);
        }
}
