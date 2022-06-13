package com.example.printer_task.service;

import com.example.printer_task.entity.Department;
import com.example.printer_task.entity.enums.Region;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.DepartmentDto;
import com.example.printer_task.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    final DepartmentRepository departmentRepository;
    public ApiResponse<List<Department>> getAll() {
        List<Department> all = departmentRepository.findAll();
        return new ApiResponse<>("Departmentlar ro'yxati",true,all);
    }

    public ApiResponse<Department> getOne(Long id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse<>("Bunday id lik department yo'q",false);
        }
        return new ApiResponse<>(" Mana",true,byId.get());
    }

    public ApiResponse<Department> add(DepartmentDto departmentDto) {
        boolean b = departmentRepository.existsByIndex(departmentDto.getIndex());
        if(b){
            return new ApiResponse<>("Bunday indexli department mavjud",false);
        }
        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setIndex(departmentDto.getIndex());
        Region region = Region.valueOf(departmentDto.getRegion().toUpperCase());
        department.setRegion(region);
        departmentRepository.save(department);
        return new ApiResponse<>("Qoshildi",true);
    }

    public ApiResponse<Department> edit(Long id, DepartmentDto departmentDto) {
        Optional<Department> byId = departmentRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse<>("Bunday id lik Department yo'q",false);
        }
        Department department = byId.get();
        department.setName(departmentDto.getName());
        department.setIndex(departmentDto.getIndex());
        Region region = Region.valueOf(departmentDto.getRegion().toUpperCase());
        department.setRegion(region);
        departmentRepository.save(department);
        return new ApiResponse<>("Department update qilindi",true);
    }

    public ApiResponse<Department> delete(Long id) {
        departmentRepository.deleteById(id);
        return new ApiResponse<>("Department delete qilindi",true);
    }
}
