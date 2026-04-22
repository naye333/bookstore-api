package com.taller.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ApiErrorResponse {

    private String status;        // "error"
    private int code;             // 400, 404, 500...
    private String message;       // mensaje general
    private List<String> errors;  // lista de errores
    private Instant timestamp;    // fecha y hora
    private String path;          // endpoint donde ocurrió el error
}