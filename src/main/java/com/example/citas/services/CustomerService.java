package com.example.citas.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.citas.dto.customer.AdresResponseDTO;
import com.example.citas.dto.customer.CustomerResponseDTO;
import com.example.citas.excepcion.CustomerExceptio;
import com.example.citas.models.Customer;
import com.example.citas.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public static CustomerResponseDTO convertirResponseCustomer(Customer customer) {
        AdresResponseDTO adrresResoibse = AdresResponseDTO.builder().city(customer.getAdres().getCity())
                .mainSteet(customer.getAdres().getMainSteet()).secondStreet(customer.getAdres().getSecondStreet())
                .build();
        return CustomerResponseDTO.builder().idCustomer(customer.getIdCustomer()).firsName(customer.getFirsName())
                .lastName(customer.getLastName()).email(customer.getEmail()).adresResponseDTO(adrresResoibse).build();
    }

    public CustomerResponseDTO getByfirsName(String firsName) {

        Customer customer = this.customerRepository.findByFirsName(firsName)
                .orElseThrow(() -> new CustomerExceptio("Usuario no encontrado con ese nombre"));
        return convertirResponseCustomer(customer);
    }

    public List<CustomerResponseDTO> getAllFistName(String fisName) {
        List<Customer> customers = this.customerRepository.findByFirsNameContaining(fisName);
        if (!customers.isEmpty()) {
            return customers.stream().map((customer) -> convertirResponseCustomer(customer)).toList();
        } else {
            throw new CustomerExceptio("ERROR AL OBTENER LOS DATOS");
        }
    }

    public List<CustomerResponseDTO> getByAdres(String city) {
        List<Customer> res = this.customerRepository.findByAdres_City(city);
        if (!res.isEmpty()) {
            return res.stream().map((x) -> convertirResponseCustomer(x)).toList();
        } else {
            throw new CustomerExceptio("error city no encontrado");
        }
    }

    public CustomerResponseDTO getByEmail(String email) {
        Customer cu = this.customerRepository.getByEmailCustomer(email);
        if (cu != null) {
            return convertirResponseCustomer(cu);
        } else {
            throw new CustomerExceptio("email no existente");
        }
    }

    public Map<String, String> getNameByEmail(String email) {
        Map<String, String> res = new HashMap<>();
        res.put("firsName", this.customerRepository.getNameByEmail(email));
        if (!res.isEmpty()) {
            return res;
        } else {
            throw new CustomerExceptio("nombre no encontrado");
        }

    }

    public Map<String, Object> getCustomerByQueryNative(String email) {

        Map<String, Object> res = new HashMap<>();
        Customer customer = this.customerRepository.getByEnailNative(email);
        if (customer != null) {
            res.put("estatus", "usuario ecnontrado co email" + email);
            res.put("data", convertirResponseCustomer(customer));
            return res;
        } else {
            throw new CustomerExceptio("usuario no encontrado con  email : " + email);
        }

    }

    public Map<String, Object> getByNativeParam(String email) {
        Map<String, Object> res = new HashMap<>();
        Customer customer = this.customerRepository.getByParamCustomerNative(email);
        if (customer != null) {
            res.put("estatus", "usuario enecontrado con email " + email);
            res.put("data", convertirResponseCustomer(customer));
            return res;
        } else {
            throw new CustomerExceptio("usuario no registradop o encontrado con " + email);
        }
    }

    @Transactional
    public Map<String, String> updateFirsname(String firsName, String email) {
        Map<String, String> res = new HashMap<>();
        if (this.customerRepository.getByEmailCustomer(email) != null) {
            this.customerRepository.updateEmailByEmail(firsName, email);
            res.put("estatus", "usuario actualizado con email" + email);
            return res;
        } else {
            throw new CustomerExceptio("exception no se encuentro el usuario");
        }

    }

}
