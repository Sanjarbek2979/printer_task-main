package com.example.printer_task.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO {
    @NotNull(message = "Username bo`sh bo`lmasligi kerak")
    private String username;
    @NotNull(message = "initialPassword bo`sh bo`lmasligi kerak")
    private String initialPassword;
    @NotNull(message = "newPassword bo`sh bo`lmasligi kerak")
    private String newPassword;
}
