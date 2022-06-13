package com.example.printer_task.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicesDto {

    @NotNull(message = "Service name null bo`lishi mumkin emas")
    private String name;
    @NotNull(message = "Service price null bo`lishi mumkin emas")
    private Long price;
}
