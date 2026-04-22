package com.taller.bookstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class BookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @Positive
    private double price;

    @Min(0)
    private int stock;

    private Long authorId;
    private List<Long> categoryIds;
}