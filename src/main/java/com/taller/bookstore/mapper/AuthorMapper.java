package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequest request) {
        return Author.builder()
                .name(request.getName())
                .bio(request.getBio())
                .contact(request.getContact())
                .build();
    }

    public AuthorResponse toResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .contact(author.getContact())
                .build();
    }
}