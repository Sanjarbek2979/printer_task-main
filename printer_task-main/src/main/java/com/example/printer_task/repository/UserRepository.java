package com.example.printer_task.repository;


import com.example.printer_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String userName,String password);
    boolean existsByUsernameAndPassword(String userName,String password);
}
