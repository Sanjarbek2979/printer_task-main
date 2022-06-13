package com.example.printer_task.repository;

import com.example.printer_task.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByIndex(Integer index);
}
