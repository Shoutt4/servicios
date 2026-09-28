package com.example.citas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.citas.models.Customer;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByFirsName(String firsName);

    List<Customer> findByFirsNameContaining(String firsName);

    List<Customer> findByAdres_City(String city);

    @Query("SELECT c FROM Customer c WHERE c.email=?1")
    Customer getByEmailCustomer(
            @Param("email") String email);

    @Query("SELECT c.firsName FROM Customer c WHERE c.email=?1 ")
    String getNameByEmail(String email);

    
}
