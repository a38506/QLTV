package com.libmanagement.web.controllers;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    // Spring Boot
    private final BookService _bookService;
    BookController(BookService bookService) {
        _bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return _bookService.getAllBooks();
    }

    @PostMapping()
    public void addNewBook(Book book) {
        _bookService.addBook(book.getName_Book(), book.get_Author(), book.get_Major());
    }
}
