package com.example.citas.dto.local;

import com.example.citas.dto.manager.ManagerRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LocalRequestDTO {
    private String name;
    private String floor;
    private ManagerRequestDTO requestManagerDto;
}
