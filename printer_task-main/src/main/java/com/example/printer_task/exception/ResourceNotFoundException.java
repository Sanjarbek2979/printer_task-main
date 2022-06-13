package com.example.printer_task.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String notification, String id, UUID id1) {
    }
}
