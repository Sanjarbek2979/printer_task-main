package com.example.printer_task.repository;

import com.example.printer_task.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
    public List<Application> findAllByDateEquals(Timestamp date);
}
