package com.example.citas.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {
    private String idCustomer;
    private String firsName;
    private String lastName;
    private String email;
    private AdresResponseDTO adresResponseDTO;
}
