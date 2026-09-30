package com.example.citas.dto.local;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLocalCustomerDTO {
    private String uuideLocal;
    private List<String> uidCustumerList;
}
