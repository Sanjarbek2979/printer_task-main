package com.example.printer_task.repository;

import com.example.printer_task.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
    boolean existsByValue(String value);
}
