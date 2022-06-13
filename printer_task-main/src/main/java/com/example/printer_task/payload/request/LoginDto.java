package com.example.printer_task.payload.request;


import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginDto {

    @NotNull(message = "Username null bo`lishi mumkin emas")
    private String username;

    @NotNull(message = "Password null bo`lishi mumkin emas")
    private String password;
}
