package com.example.printer_task.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelDto {

    @NotNull(message = "Receiver null bo`lishi mumkin emas")
    private String name;

}
