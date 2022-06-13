package com.example.printer_task.service;

import com.example.printer_task.entity.Permission;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.PermissionDto;
import com.example.printer_task.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {
    final PermissionRepository permissionRepository;
    public ApiResponse<List<Permission>> getAll() {
        List<Permission> all = permissionRepository.findAll();
        return new ApiResponse<>("Permissionlar listi",true,all);
    }

    public ApiResponse<Permission> getOne(Long id) {
        Optional<Permission> byId = permissionRepository.findById(id);

        if(byId.isEmpty()){
            return new ApiResponse<>("Bunday id li permission mavjud emas",false);
        }
        return new ApiResponse<>("Mana",true,byId.get());

    }

    public ApiResponse<Permission> add(PermissionDto permissionDto) {
        boolean b = permissionRepository.existsByValue(permissionDto.getValue());
        if(b){
            return new ApiResponse<>("Bunday permission allaqachon mavjud",false);
        }
        Permission permission=new Permission();
        permission.setValue(permissionDto.getValue());
        Permission save = permissionRepository.save(permission);
        return new ApiResponse<>("Qoshildi",true,save);
    }

    public ApiResponse<Permission> edit(Long id, PermissionDto permissionDto) {
        Optional<Permission> byId = permissionRepository.findById(id);
        if(byId.isEmpty()){
            return new ApiResponse<>("Bunday id lik permission mavjud emas",false);
        }
        Permission permission = byId.get();
        permission.setValue(permissionDto.getValue());
        permissionRepository.save(permission);
        return new ApiResponse<>("Permission update qilindi",true);

    }

    public ApiResponse<Permission> delete(Long id) {
        permissionRepository.deleteById(id);
        return new ApiResponse<>("Permission deleted",true);
    }
}
