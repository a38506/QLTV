package com.libmanagement.controllers;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.services.BookService;
import com.libmanagement.db.DataStore;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {
    // Spring Boot
    private final BookService _bookService;

    @Autowired
    BookController(BookService bookService) {
        _bookService = bookService;
    }

    //Lấy tât cả bao gồm  sách  đã bị xóa
//    @GetMapping
//    public List<Book> getAllBooks() {
//        return _bookService.getAll();
//    }

    //Lấy tất cả không bao gồm sách bị xóa
    @GetMapping
    public List<Book> getAll() {
        return DataStore.books.stream().filter(book-> !book.Deleted).collect (Collectors.toList( ));
    }


    @PostMapping()
    public void addNewBook(Book book) {
        try {    
            _bookService.add(book);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/remove/{Id}")
        public void remove(@PathVariable String Id) {
            try {    
                _bookService.removeById(Id);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
    

    @PutMapping("/{bookId}")
    public void update(@PathVariable String bookId, @RequestBody Book bookToUpdate) {
        try {
            _bookService.update(bookToUpdate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // @DeleteMapping("/remove/{Id}") 
    // public void removeBook(@PathVariable String Id){
    //     _bookService.remove(Id);
    // }
}
