package com.libmanagement.web.controllers;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.services.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        return _bookService.getAll();
    }

    @PostMapping()
    public void addNewBook(Book book) {
        _bookService.add(book);
    }

    @DeleteMapping()
    public void removeBook(Book book){
        _bookService.remove(book.ID);
    }

    @PutMapping("/{bookId}")
    public void update(@PathVariable String bookId, @RequestBody Book bookToUpdate) {
        Book res = _bookService.getById(bookId);
        if (res == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
 //           throw new RuntimeException(MessageFormat.format ("Book With Id is {0} Not Found",bookToUpdate.ID));
        }

        res.Name = bookToUpdate.Name;
        res.Author =bookToUpdate.Author;
        res.Major = bookToUpdate.Major;
        return; 

    }
}
