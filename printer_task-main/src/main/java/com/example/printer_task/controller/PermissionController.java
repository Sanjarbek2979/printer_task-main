package com.example.printer_task.controller;


import com.example.printer_task.entity.Permission;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.PermissionDto;
import com.example.printer_task.repository.PermissionRepository;
import com.example.printer_task.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
public class PermissionController {
    final PermissionService permissionService;
    final PermissionRepository permissionRepository;
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse<List<Permission>> apiResponse=permissionService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne( @PathVariable Long id){
        ApiResponse<Permission> apiResponse=permissionService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.FOUND :HttpStatus.NOT_FOUND).body(apiResponse);
    }


    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody PermissionDto permissionDto){
        ApiResponse<Permission> apiResponse= permissionService.add(permissionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED :HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id,@Valid @RequestBody PermissionDto permissionDto){
        ApiResponse<Permission> apiResponse=permissionService.edit(id,permissionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<Permission> apiResponse=permissionService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

}
