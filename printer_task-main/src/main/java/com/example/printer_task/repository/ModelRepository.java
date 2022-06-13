package com.example.printer_task.repository;

import com.example.printer_task.entity.Model;
import com.example.printer_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model,Long> {
    Optional<Model> findByName(String name);

}
