package com.example.printer_task.service;

import com.example.printer_task.entity.Permission;
import com.example.printer_task.entity.Role;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.RoleDto;
import com.example.printer_task.repository.PermissionRepository;
import com.example.printer_task.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    final RoleRepository roleRepository;
    final PermissionRepository permissionRepository;


    public ApiResponse<List<Role>> getAll() {
        return new ApiResponse<>("List", true, roleRepository.findAll());
    }

    public ApiResponse<Role> getOne(Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResponse<>("Xatolik", false);
        }
        Role role = byId.get();
        return new ApiResponse<>("Mana", true, role);
    }

    public ApiResponse deleteRole(Long id) {
        roleRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }

    public ApiResponse addRole(RoleDto roleDto) {
        Optional<Role> byName = roleRepository.findByName(roleDto.getName());
        if (byName.isPresent()) {
            return new ApiResponse("Bunday name li role oldindan mavjud", false);
        }
        Role role = new Role();
        role.setName(roleDto.getName());

        List<Permission> allById = permissionRepository.findAllById(roleDto.getPermissionIds());
        role.setPermissions(allById);
        Role save = roleRepository.save(role);
        return new ApiResponse("saved", true, save);
    }

    public ApiResponse<Role> editRole(Long id, RoleDto roleDto) {

        Optional<Role> byId = roleRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse<>("Role topilmadi", false);
        }
        Role role = byId.get();

        role.setName(roleDto.getName());

        List<Permission> allById = permissionRepository.findAllById(roleDto.getPermissionIds());
        role.setPermissions(allById);
        Role save = roleRepository.save(role);
        return new ApiResponse("Updated", true, save);
    }
}
