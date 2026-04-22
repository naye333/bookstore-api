package com.taller.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String bio;

    private String contact;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;
}