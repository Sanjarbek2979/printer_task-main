package com.example.printer_task.controller;


import com.example.printer_task.entity.Application;
import com.example.printer_task.payload.request.ApplicationDto;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {
    final ApplicationService applicationService;

    @PostMapping("/save")
    public HttpEntity<?> saveApplication(@RequestBody ApplicationDto applicationDto){
        ApiResponse<Application> apiResponse=applicationService.save(applicationDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        ApiResponse<Application> apiResponse=applicationService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse<List<Application>> apiResponse=applicationService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

}
