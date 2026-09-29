package com.example.citas.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.citas.dto.local.LocalRequestDTO;
import com.example.citas.dto.local.LocalResponseDTO;
import com.example.citas.dto.manager.ManagerRequestDTO;
import com.example.citas.models.Local;
import com.example.citas.models.Manager;
import com.example.citas.repository.LocalRepository;

@Service
public class LocalService {
    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
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
}
