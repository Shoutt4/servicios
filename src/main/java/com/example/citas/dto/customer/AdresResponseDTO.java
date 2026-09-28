package com.example.citas.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdresResponseDTO {
    private String city;
    private String mainSteet;
    private String secondStreet;

}
