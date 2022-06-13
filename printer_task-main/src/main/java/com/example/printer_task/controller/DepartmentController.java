package com.example.printer_task.controller;

import com.example.printer_task.entity.Department;
import com.example.printer_task.entity.enums.Region;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.DepartmentDto;
import com.example.printer_task.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080")
@RequestMapping("/department")
public class DepartmentController {
    final DepartmentService departmentService;
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse<List<Department>> apiResponse=departmentService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @GetMapping("/regions")
    public HttpEntity<?> getAllRegions(){
        ApiResponse apiResponse= new ApiResponse("Mana",true, Region.values());
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        ApiResponse<Department> apiResponse=departmentService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse<Department> apiResponse=departmentService.add(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id,@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse<Department> apiResponse=departmentService.edit(id,departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<Department> apiResponse=departmentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
