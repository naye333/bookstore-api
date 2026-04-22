package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.entity.Book;
import com.taller.bookstore.repository.AuthorRepository;
import com.taller.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // 🔥 NUEVO: usar DTO
    public Book save(BookRequest request) {
        System.out.println("AUTHOR ID: " + request.getAuthorId());
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setStock(request.getStock());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    // 🔥 UPDATE con DTO
    public Book update(Long id, BookRequest request) {

        Book book = findById(id);

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setStock(request.getStock());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}