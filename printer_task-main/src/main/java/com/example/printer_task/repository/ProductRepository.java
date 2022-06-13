package com.example.printer_task.repository;


import com.example.printer_task.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByActiveTrue();

    boolean existsByName(String name);

    Optional<Product> findByIdAndActiveTrue(Long id);
}
