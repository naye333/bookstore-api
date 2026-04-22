package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.request.RegisterRequest;
import com.taller.bookstore.dto.response.UserResponse;
import com.taller.bookstore.entity.Role;
import com.taller.bookstore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.ROLE_USER)
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}