package com.example.printer_task.service;

import com.example.printer_task.component.Generator;
import com.example.printer_task.entity.Department;
import com.example.printer_task.entity.Role;
import com.example.printer_task.entity.User;
import com.example.printer_task.payload.request.ChangePasswordDTO;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.UserDto;
import com.example.printer_task.repository.DepartmentRepository;
import com.example.printer_task.repository.RoleRepository;
import com.example.printer_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final DepartmentRepository departmentRepository;

    public ApiResponse<User> registerUser(UserDto userDto) {
        User user = new User();
        Optional<User> byUsername = userRepository.findByUsername(userDto.getUsername());
        if (byUsername.isPresent()) {
            return new ApiResponse<>("Bunday usernameli user mavjud", false);
        }
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(Generator.passwordEncoder().encode(userDto.getPassword()));
//        Optional<Role> byId = roleRepository.findById(userDto.getRoleId());
//        if (byId.isEmpty()) {
//            return new ApiResponse<>("Bunday id li role yoq", false);
//        }

        user.setRole(roleRepository.getById(2L));
        Optional<Department> byId1 = departmentRepository.findById(userDto.getDepartmentId());
        if (byId1.isEmpty()) {
            return new ApiResponse<>("Bunday id li department yoq", false);
        }

        user.setDepartment(byId1.get());
        User save = userRepository.save(user);

        return new ApiResponse<>("User muvaffaqiyatli qoshildi", true, save);

    }

    public ApiResponse changePassword(ChangePasswordDTO changePasswordDTO) {
        Optional<User> byUsernameAndPassword = userRepository.findByUsernameAndPassword(changePasswordDTO.getUsername(),changePasswordDTO.getInitialPassword());
if (byUsernameAndPassword.isEmpty()){
    return new ApiResponse("Xatolik",false);
}
        User user = byUsernameAndPassword.get();
user.setPassword(changePasswordDTO.getNewPassword());
userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatli o`zgartirildi",true);
    }

    public ApiResponse getAll() {
        List<User> all = userRepository.findAll();
        return new ApiResponse("All user's list",true,all);
    }

    public ApiResponse blockUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("Bu user topilmadi",false);
        }
        User user = byId.get();
        user.setEnabled(false);
        userRepository.save(user);
        return new ApiResponse("User blocked successfully",true);
    }

    public ApiResponse unblockUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("Bu user topilmadi",false);
        }
        User user = byId.get();
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponse("User blocked successfully",true);
    }
}
