package com.example.printer_task.payload.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BrandDto {

    @NotNull(message = "Brand name null bo`lishi mumkin emas")
    private String name;


}
