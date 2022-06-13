package com.example.printer_task.repository;

import com.example.printer_task.entity.ServiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesProductRepository extends JpaRepository<ServiceProduct,Long> {
}
