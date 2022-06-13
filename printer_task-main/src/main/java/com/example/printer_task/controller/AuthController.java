package com.example.printer_task.controller;

import com.example.printer_task.component.Generator;
import com.example.printer_task.entity.User;
import com.example.printer_task.payload.request.LoginDto;
import com.example.printer_task.payload.response.ApiResponse;
import com.example.printer_task.repository.UserRepository;
import com.example.printer_task.security.JwtProvider;
import com.example.printer_task.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://172.18.45.66:8080/",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT},allowedHeaders = {"Origin","Content-Type","Authorization"})

@RequiredArgsConstructor
public class AuthController {
    final AuthService authService;
    final JwtProvider jwtProvider;
    final UserRepository userRepository;
final Generator generator;

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDto ) {

        Optional<User> byUsernameAndPassword = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (byUsernameAndPassword.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Username or password incorrect", false));
        String token = jwtProvider.generateToken(loginDto.getUsername());
        return ResponseEntity.ok().body(token);
    }
    @GetMapping("/me")
    public HttpEntity<?> currentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(200).body(currentUser);
    }
/*
    @PostMapping("/check")
    public HttpEntity<?> checkUser(@Valid @RequestBody LoginDto loginDto) {
        boolean b = userRepository.existsByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (b) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Username or password correct", true));
        } else

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Username or password correct", false));
    }*/

}
