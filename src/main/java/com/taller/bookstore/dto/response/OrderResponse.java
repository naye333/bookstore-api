package com.taller.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long id;
    private double total;
    private String status;
    private List<OrderItemResponse> items;
}