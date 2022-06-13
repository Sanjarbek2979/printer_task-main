package com.example.printer_task.controller;


import com.example.printer_task.entity.Services;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.ServicesDto;
import com.example.printer_task.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {
    final ServicesService servicesService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse<List<Services>> all = servicesService.getAll();

        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<Services> apiResponse = servicesService.getOne(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addServices(@Valid @RequestBody ServicesDto servicesDto) {
        ApiResponse apiResponse = servicesService.addServices(servicesDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteServices(@PathVariable Long id) {
        ApiResponse apiResponse = servicesService.deleteServices(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editServices(@PathVariable Long id,@Valid @RequestBody ServicesDto servicesDto) {
        ApiResponse<Services> apiResponse = servicesService.editServices(id, servicesDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
