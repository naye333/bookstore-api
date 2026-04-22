package com.taller.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse<T> {

    private String status;     // "success"
    private int code;          // 200, 201, etc
    private String message;    // mensaje descriptivo
    private T data;            // datos (objeto o lista)
    private Instant timestamp; // fecha y hora
}