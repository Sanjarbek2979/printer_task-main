package com.example.printer_task.payload.request;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {

    @NotNull(message = "Product name null bo`lmasligi kerak")
    private String name;

    @NotNull(message = "Servicelari null bo`lishi mumkin emas")
    private List<Long> serviceIds;

    @NotNull
    private Long brandId;

    @NotNull
    private Long modelId;

}
