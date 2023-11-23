package com.libmanagement.controllers;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.models.BookTransaction;
import com.libmanagement.core.services.BookService;
import com.libmanagement.core.services.BookTransactionService;
import com.libmanagement.core.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

@RestController
@RequestMapping("/api/transaction") 
public class BookTransactionController {
    private final BookService _bookService;
    private final StudentService _studentService;
    private final BookTransactionService _bookTransactionService;

    @Autowired
    public BookTransactionController(BookService bookService, StudentService studentService, BookTransactionService bookTransactionService) {
        _bookService = bookService;
        _studentService = studentService;
        _bookTransactionService = bookTransactionService;
    }


    @PostMapping("/borrow")
    public void BorrowBook(String studentId, String bookId) {
        var book = _bookService.getById(bookId);
        var student = _studentService.getById(studentId);

        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
        }
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
        }

        try {
            _bookTransactionService.add(new BookTransaction("", bookId, studentId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{studentId}")
    public List<Book> getAllBooksByStudentId(@PathVariable("studentId") String studentId) {
        var bookTransactions = _bookTransactionService.getByStudentId(studentId);
        List<Book> result = new ArrayList<>();
        for (var bookTransaction : bookTransactions) {
            result.add(_bookService.getById(bookTransaction.bookId));
        }
        return result;
    }


    @DeleteMapping("/return/{Id}")
    public void returnBook(@PathVariable String Id) {
        try {
            _bookTransactionService.returnBook(Id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/total/borrowed")
    public int getTotalBorrowedBooks() {
        return _bookTransactionService.getTotalBorrowedBooks();
    }


    @GetMapping("/total")
    public int getTotalBooks() {
        return _bookService.getTotalBooks();
    }


    @GetMapping("/total/borrowed/{studentId}")
    public List<Book> getTotalBorrowedBooksByStudentId(@PathVariable String studentId) {
        List<BookTransaction> bookTransactions = _bookTransactionService.getByStudentId(studentId);

        return bookTransactions.stream().filter(bookTransaction -> !_studentService.isDeleted(bookTransaction.Id))
                .map(bookTransaction -> _bookService.getById(bookTransaction.Id)).toList();
    }

    @GetMapping("/borrowing")
    public int getTotalBorrowedBooksCount() {
        return _bookTransactionService.getTotalBorrowingBooksCount();

    }
}

