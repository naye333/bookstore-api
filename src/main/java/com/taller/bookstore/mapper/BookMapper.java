package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.entity.Book;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book toEntity(BookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }

    public BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .stock(book.getStock())
                .authorName(book.getAuthor().getName())
                .categories(
                        book.getCategories()
                                .stream()
                                .map(cat -> cat.getName())
                                .collect(Collectors.toList())
                )
                .build();
    }
}