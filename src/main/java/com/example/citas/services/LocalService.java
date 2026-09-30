package com.example.citas.services;

import com.example.citas.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.example.citas.dto.local.LocalRequestDetaillsDTO;
import com.example.citas.dto.local.LocalRequestDTO;
import com.example.citas.dto.local.LocalResponseDTO;
import com.example.citas.dto.local.RequestLocalCustomerDTO;
import com.example.citas.dto.manager.ManagerRequestDTO;
import com.example.citas.dto.orden.OrdenDTO;
import com.example.citas.models.Customer;
import com.example.citas.models.Local;
import com.example.citas.models.Manager;
import com.example.citas.models.Orden;
import com.example.citas.repository.LocalRepository;
import com.example.citas.excepcion.LocalException;

@Service
public class LocalService {
    private final CustomerRepository customerRepository;
    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository, CustomerRepository customerRepository) {
        this.localRepository = localRepository;
        this.customerRepository = customerRepository;
    }

    public static Local convertirRequet(LocalRequestDTO requestDTO) {
        Manager manager = Manager.builder().firsName(requestDTO.getRequestManagerDto().getFirsName())
                .lastName(requestDTO.getRequestManagerDto().getLastName()).build();
        return Local.builder().name(requestDTO.getName()).floor(requestDTO.getFloor()).manager(manager).build();
    }

    public static LocalResponseDTO convertirResponse(Local local) {
        return LocalResponseDTO.builder().localId(local.getLocalId()).name(local.getName()).floor(local.getFloor())
                .build();
    }

    public Map<String, Object> saveLocal(LocalRequestDTO request) {
        Map<String, Object> res = new HashMap<>();
        res.put("estatus", "Local creado correctamente");
        res.put("data", convertirResponse(this.localRepository.save(convertirRequet(request))));
        return res;
    }

    private static Orden convertirOrdenRequest(OrdenDTO orden) {
        return Orden.builder().descripcion(orden.getDescripcion()).price(orden.getPrice()).build();
    }

    private Local convertirResponseDetails(LocalRequestDetaillsDTO request) {
        List<Orden> ordenList;
        Manager manager = Manager.builder().firsName(request.getRequestManagerDto().getFirsName())
                .lastName(request.getRequestManagerDto().getLastName()).build();
        ordenList = request.getOrdenDTOs().stream().map(e -> convertirOrdenRequest(e)).toList();
        return Local.builder().name(request.getName()).floor(request.getFloor()).manager(manager).ordens(ordenList)
                .build();

    }

    public Map<String, Object> saveLocalAll(LocalRequestDetaillsDTO request) {
        Map<String, Object> res = new HashMap<>();
        if (!this.localRepository.findByName(request.getName()).isPresent()) {
            res.put("status", "Local creado correctamente");
            res.put("data", convertirResponse(this.localRepository.save(convertirResponseDetails(request))));
            return res;
        } else {
            throw new LocalException("error al crear el local");
        }
    }

    public Map<String, Object> saveUid(RequestLocalCustomerDTO request) {
        Map<String, Object> res = new HashMap<>();
        List<Customer> customer = request.getUidCustumerList().stream()
                .map((id) -> this.customerRepository.findById(id).get()).toList();
        Local local = this.localRepository.findById(request.getUuideLocal())
                .orElseThrow(() -> new LocalException("local no encontrado con id " + request.getUuideLocal()));

        if (!customer.isEmpty()) {
            res.put("status", "locales creados");
            res.put("data", this.localRepository.save(local.builder().customerList(customer).build()));
            return res;
        } else {
            throw new LocalException("ereror xD");
        }

    }
}
