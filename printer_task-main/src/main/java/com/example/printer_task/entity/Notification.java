package com.example.printer_task.entity;

import com.example.printer_task.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Notification extends AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String message;
    private UUID userOrderId;
//    @Enumerated(EnumType.STRING)
    private String messageType;
    private boolean seen;
    private UUID userId;


}
