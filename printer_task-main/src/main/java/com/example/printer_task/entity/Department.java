package com.example.printer_task.entity;

import com.example.printer_task.entity.enums.Region;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private Integer index;

    @Enumerated(EnumType.STRING)
    private Region region;

//    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
//    private List<User> employees;

    private boolean active=true;
}
