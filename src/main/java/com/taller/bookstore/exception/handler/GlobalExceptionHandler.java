package com.taller.bookstore.exception.handler;

import com.taller.bookstore.dto.response.ApiErrorResponse;
import com.taller.bookstore.exception.custom.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), 404, request, List.of(ex.getMessage()));
    }

    // 409
    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleDuplicate(DuplicateResourceException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), 409, request, List.of(ex.getMessage()));
    }

    // 422
    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiErrorResponse handleStock(InsufficientStockException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), 422, request, List.of(ex.getMessage()));
    }

    // 403
    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleUnauthorized(UnauthorizedAccessException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), 403, request, List.of(ex.getMessage()));
    }

    // 409
    @ExceptionHandler(InvalidOrderStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleInvalidOrder(InvalidOrderStateException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), 409, request, List.of(ex.getMessage()));
    }

    // 409
    @ExceptionHandler(AuthorHasBooksException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleAuthorHasBooks(AuthorHasBooksException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), 409, request, List.of(ex.getMessage()));
    }

    // 400 VALIDACIONES (MUY IMPORTANTE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        return buildResponse("Error de validación", 400, request, errors);
    }

    // 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleGeneral(Exception ex, HttpServletRequest request) {
        return buildResponse("Error interno del servidor", 500, request, List.of(ex.getMessage()));
    }

    // Metodo helper
    private ApiErrorResponse buildResponse(String message, int code, HttpServletRequest request, List<String> errors) {
        return ApiErrorResponse.builder()
                .status("error")
                .code(code)
                .message(message)
                .errors(errors)
                .timestamp(Instant.now())
                .path(request.getRequestURI())
                .build();
    }
}