package com.example.citas.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.citas.dto.customer.CustomerResponseDTO;
import com.example.citas.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/firsname")
    public ResponseEntity<CustomerResponseDTO> getCustormerFisName(@RequestParam String fisName) {
        return ResponseEntity.accepted().body(this.customerService.getByfirsName(fisName));

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerResponseDTO>> getAllByFisname(@RequestParam String firsName) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.getAllFistName(firsName));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/byCity")
    public ResponseEntity<List<CustomerResponseDTO>> getByCity(@RequestParam String city) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.getByAdres(city));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/byEmail")
    public ResponseEntity<CustomerResponseDTO> getByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.getByEmail(email));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ByUniqueName")
    public ResponseEntity<Map<String, String>> getByname(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.getNameByEmail(email));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/byNative")
    public ResponseEntity<Map<String, Object>> getByNative(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.getCustomerByQueryNative(email));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ByParamNative")

    public ResponseEntity<Map<String, Object>> getByParamNative(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.getByNativeParam(email));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateFirsNameByEmail(@RequestParam("firstName") String firstName,
            @RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.updateFirsname(firstName, email));
    }
}
