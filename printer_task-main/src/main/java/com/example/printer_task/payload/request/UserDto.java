package com.example.printer_task.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    @NotNull(message = "Fullname null bo`lishi mumkin emas")
    private String fullName;

    @NotBlank(message = "Username bosh bolmasligi kerak")
    private String username;

    @NotNull(message = "Password not be entered")
    private String password;

//    @NotNull(message = "Role kiritilmadi")
//    private Long roleId;

    @NotNull(message = "Department biriktirilmadi")
    private Long departmentId;
}

