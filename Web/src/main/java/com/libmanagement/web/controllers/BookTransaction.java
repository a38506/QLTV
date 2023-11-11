package com.libmanagement.web.controllers;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.services.BookService;
import com.libmanagement.core.models.BookTransaction;
import com.libmanagement.core.services.BookTransactionService;
import com.libmanagement.core.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.events.Event.ID;

import java.util.*;

@RestController
@RequestMapping("/api/management") // decorator
public class BookTransaction {
    private final BookService _bookService;
    private final StudentService _studentService;
    private final BookTransactionService _bookTransactionService;


    public BookTransaction(BookService bookService, StudentService studentService, BookTransactionService bookTransactionService) {
        _bookService = bookService;
        _studentService = studentService;
        _bookTransactionService = bookTransactionService;
    }

 
    @PostMapping("/borrow")
    public void BorrowBook(String studentId, String bookId) {
        var book = _bookService.getById(bookId);
        var student = _studentService.getById(studentId);

        if (book == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book Not Found");
        }

        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found");
        }

        _bookTransactionService.add(new BookTransaction(bookId, studentId));
    }


    @GetMapping("/{studentId}")
    public List<Book> getAllBooksByStudentId(@PathVariable("studentId") String studentId) {
        var bookTransactions = _bookTransactionService.getByStudentId(studentId);
        List<Book> result = new ArrayList<>();
        for (var bookTransaction: bookTransactions) {
            result.add(_bookService.getById(ID).get(0));
        }
        return result;
    }


    @DeleteMapping("/{studentId}/{bookId}")
    public void returnBook(@PathVariable String studentId, @PathVariable String bookId) {
        _bookTransactionService.remove(bookId, studentId);
    }


    @GetMapping("/total-borrowed/{studentId}")
    public int getTotalBorrowedBooks(@PathVariable("studentId") String studentId) {
        var bookTransactions = _bookTransactionService.getByStudentId(studentId);
        return bookTransactions.size();       
    }


    @GetMapping("/total- in stock/{studentId}")
    public int getTotalBooksInStock(@PathVariable("studentId") String studentId) {

        List<Book> allBooks = _bookService.getAll();
        List<BookTransaction> allTransactions = _bookTransactionService.getAll();

        int totalBorrowedBooks = allTransactions.size();
        int totalBooksInStock = allBooks.size() - totalBorrowedBooks;

        return totalBooksInStock;
}}

