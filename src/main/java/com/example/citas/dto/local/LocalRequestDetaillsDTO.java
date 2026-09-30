package com.example.citas.dto.local;

import java.util.List;

import com.example.citas.dto.manager.ManagerRequestDTO;
import com.example.citas.dto.orden.OrdenDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalRequestDetaillsDTO {
    private String name;
    private String floor;
    private ManagerRequestDTO requestManagerDto;
    private List<OrdenDTO> ordenDTOs;

}
