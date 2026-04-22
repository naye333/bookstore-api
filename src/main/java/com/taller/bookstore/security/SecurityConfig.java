package com.taller.bookstore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // 🔓 públicos
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // 👤 USER y ADMIN
                        .requestMatchers("/api/v1/orders/**").hasAnyRole("USER", "ADMIN")

                        // 👑 SOLO ADMIN
                        .requestMatchers("/api/v1/books/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/authors/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/categories/**").hasRole("ADMIN")

                        // 🔒 resto protegido
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}