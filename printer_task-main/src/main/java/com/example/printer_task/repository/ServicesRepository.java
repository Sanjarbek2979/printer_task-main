package com.example.printer_task.repository;

import com.example.printer_task.entity.Role;
import com.example.printer_task.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    Optional<Services> findByName(String name);
}
