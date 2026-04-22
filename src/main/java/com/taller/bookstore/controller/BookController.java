package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.entity.Book;
import com.taller.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }


    @PostMapping
    public Book createBook(@RequestBody BookRequest request) {
        return bookService.save(request);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
