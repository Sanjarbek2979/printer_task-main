package com.example.printer_task.entity;

import com.example.printer_task.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "applications")
public class Application extends AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @ManyToMany
    @Column(nullable = false)
    private List<ServiceProduct> serviceProducts;


//    @Temporal(TemporalType.DATE)
    private Timestamp date;


    @ManyToOne
//    @Column(nullable = false)
    private Department senderDepartment;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Department receiverDepartment;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private User responsibleStaff;

    private boolean active=true;



}
