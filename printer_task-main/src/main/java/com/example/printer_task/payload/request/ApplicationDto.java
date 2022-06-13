package com.example.printer_task.payload.request;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplicationDto {


    private String description;
    @NotNull(message = "Receiver null bo`lishi mumkin emas")
    private Long receiverId;

    @NotNull(message = "Product lar null bo`lishi mumkin emas")
    private long[] productIds;

    @NotNull(message = "Service lar null bo`lishi mumkin emas")
    private long[] serviceIds;

    @NotNull(message = "Amount null bo`lishi mumkin emas")
    private long[] amount;
}
