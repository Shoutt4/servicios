package com.example.citas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.citas.models.Customer;

import jakarta.transaction.Transactional;

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

    @Query(value = "SELECT * FROM servicio.tbl_customer WHERE email_adres=?1", nativeQuery = true)
    Customer getByEnailNative(String email);

    @Query(value = "SELECT * FROM servicio.tbl_customer WHERE email_adres= :emailAdres", nativeQuery = true)
    Customer getByParamCustomerNative(
            @Param("emailAdres") String emilAdres);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicio.tbl_customer SET firs_name =:fisName WHERE email_adres=:email", nativeQuery = true)
    void updateEmailByEmail(
            @Param("fisName") String fisName,
            @Param("email") String email);

}
