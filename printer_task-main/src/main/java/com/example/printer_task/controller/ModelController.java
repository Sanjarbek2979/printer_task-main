package com.example.printer_task.controller;


import com.example.printer_task.entity.Model;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.ModelDto;
import com.example.printer_task.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model")
public class ModelController {
    final ModelService modelService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse<List<Model>> all = modelService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<Model> apiResponse = modelService.getOne(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addModel(@Valid @RequestBody ModelDto modelDto) {
        ApiResponse apiResponse = modelService.addModel(modelDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteModel(@PathVariable Long id) {
        ApiResponse apiResponse = modelService.deleteModel(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editModel(@PathVariable Long id, @Valid @RequestBody ModelDto modelDto) {
        ApiResponse<Model> apiResponse = modelService.editModel(id, modelDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


}
