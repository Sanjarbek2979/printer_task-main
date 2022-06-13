package com.example.printer_task.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends RuntimeException {
    String xatolik = "Bunday id li mahsulot mavjud emas";

    public NotFoundException(String xatolik) {
        System.out.println("Bunday id li product mavjud emas");
    }
}
