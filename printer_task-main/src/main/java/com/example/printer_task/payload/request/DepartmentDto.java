package com.example.printer_task.payload.request;


import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DepartmentDto {

    @NotNull(message = "Department index null bo`lishi mumkin emas")
    private Integer index;

    @NotNull(message = "Department name null bo`lishi mumkin emas")
    private String name;

    @NotNull(message = "Department region null bo`lishi mumkin emas")
    private String region;
}
