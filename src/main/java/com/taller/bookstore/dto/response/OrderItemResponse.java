package com.taller.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {

    private Long bookId;
    private String bookTitle;
    private int quantity;
    private double subtotal;
}