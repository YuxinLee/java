package com.example.demo.repository;

import com.example.demo.model.Deparment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeparmentRepository extends JpaRepository<Deparment, Long> {
}
