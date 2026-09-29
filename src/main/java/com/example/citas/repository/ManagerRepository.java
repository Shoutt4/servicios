package com.example.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.citas.models.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {

}
