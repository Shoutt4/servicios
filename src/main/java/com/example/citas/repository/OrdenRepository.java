package com.example.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.citas.models.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, String> {

}
