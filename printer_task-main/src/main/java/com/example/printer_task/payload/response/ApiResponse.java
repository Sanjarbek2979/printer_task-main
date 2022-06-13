package com.example.printer_task.payload.response;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private T object;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
