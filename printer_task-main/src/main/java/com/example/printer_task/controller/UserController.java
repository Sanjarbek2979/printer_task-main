package com.example.printer_task.controller;

import com.example.printer_task.entity.User;
import com.example.printer_task.payload.request.ChangePasswordDTO;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.payload.request.UserDto;
import com.example.printer_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080")
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody UserDto userDto) {
        ApiResponse<User> userApiResponse = userService.registerUser(userDto);
        return ResponseEntity.status(userApiResponse.isSuccess() ? 200 : 409).body(userApiResponse);

    }

    @PostMapping("/change/password")
    public HttpEntity<?> changePassword( @RequestBody ChangePasswordDTO changePasswordDTO) {
        ApiResponse apiResponse = userService.changePassword(changePasswordDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = userService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }
    @GetMapping("/block/{id}")
    public HttpEntity<?> blockUser(@PathVariable Long id) {
        ApiResponse apiResponse = userService.blockUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @GetMapping("/unblock/{id}")
    public HttpEntity<?> unblockUser(@PathVariable Long id) {
        ApiResponse apiResponse = userService.unblockUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
