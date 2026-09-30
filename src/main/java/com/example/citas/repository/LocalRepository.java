package com.example.citas.repository;

import com.example.citas.models.Local;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, String> {
    Optional<Local> findByName(String name);
}
