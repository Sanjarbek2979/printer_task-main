package com.example.printer_task.controller;

import com.example.printer_task.entity.Brand;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.BrandDto;
import com.example.printer_task.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController  {
    final BrandService brandService;
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse<List<Brand>> apiResponse=brandService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(Long id){
        ApiResponse<Brand> apiResponse=brandService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody BrandDto brandDto){
        ApiResponse<Brand> apiResponse=brandService.add(brandDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public  HttpEntity<?> edit(@PathVariable Long id,@Valid @RequestBody BrandDto brandDto){
        ApiResponse<Brand> apiResponse=brandService.edit(id,brandDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED :HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<Brand> apiResponse=brandService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED :HttpStatus.CONFLICT).body(apiResponse);
    }

}
