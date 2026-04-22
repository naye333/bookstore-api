package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.LoginRequest;
import com.taller.bookstore.dto.request.RegisterRequest;
import com.taller.bookstore.dto.response.AuthResponse;
import com.taller.bookstore.dto.response.UserResponse;
import com.taller.bookstore.entity.User;
import com.taller.bookstore.exception.custom.DuplicateResourceException;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.exception.custom.UnauthorizedAccessException;
import com.taller.bookstore.mapper.UserMapper;
import com.taller.bookstore.repository.UserRepository;
import com.taller.bookstore.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedAccessException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        return AuthResponse.builder()
                .token(token)
                .expiresIn(86400000)
                .role(user.getRole().name())
                .build();
    }
}