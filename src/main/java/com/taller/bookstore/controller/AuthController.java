package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.LoginRequest;
import com.taller.bookstore.dto.request.RegisterRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.AuthResponse;
import com.taller.bookstore.dto.response.UserResponse;
import com.taller.bookstore.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

// módulo de autenticación
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@Valid @RequestBody RegisterRequest request) {

        UserResponse user = authService.register(request);

        return ApiResponse.<UserResponse>builder()
                .status("success")
                .code(200)
                .message("Usuario registrado correctamente")
                .data(user)
                .timestamp(Instant.now())
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ApiResponse.<AuthResponse>builder()
                .status("success")
                .code(200)
                .message("Login exitoso")
                .data(response)
                .timestamp(java.time.Instant.now())
                .build();
    }
}