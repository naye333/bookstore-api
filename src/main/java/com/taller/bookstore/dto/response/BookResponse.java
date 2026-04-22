package com.taller.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String isbn;
    private double price;
    private int stock;

    private String authorName;
    private List<String> categories;
}