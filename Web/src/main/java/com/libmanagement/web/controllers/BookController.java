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
import java.util.Optional;

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

    @DeleteMapping()
    public void deleteBook(Book book){
        _bookService.removeBook(book.getID_Book());
    }

    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable String bookId, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = _bookService.searchBooksByID(bookId).stream().findFirst();

        if (existingBook== null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Book Not Found");
        } 
        else {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setName_Book(updatedBook.getName_Book());
            bookToUpdate.set_Author(updatedBook.get_Author());
            bookToUpdate.set_Major(updatedBook.get_Major());
            
            _bookService.updateBook(bookId,bookToUpdate.getName_Book(),  bookToUpdate.get_Author(),bookToUpdate.get_Major());
        }    
    }
}
