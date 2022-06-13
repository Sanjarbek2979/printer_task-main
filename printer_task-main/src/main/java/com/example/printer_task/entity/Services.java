package com.example.printer_task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

//    @NotNull(message = "Service ni narxi bo`sh bo`lmasligi kerak")
////    @Column(nullable = false, unique = true)
    private Long price;

    private boolean active=true;

}
