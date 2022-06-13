package com.example.printer_task.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.awt.image.CropImageFilter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {

    @NotNull(message = "Role name  null bo`lishi mumkin emas")
    private String name;

    @NotNull(message = "Role permissions null bo`lishi mumkin emas")
    private List<Long> permissionIds;
}
