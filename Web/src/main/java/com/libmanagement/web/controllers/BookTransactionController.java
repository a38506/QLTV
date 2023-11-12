package com.libmanagement.web.controllers;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.models.BookTransaction;
import com.libmanagement.core.services.BookService;
import com.libmanagement.core.services.BookTransactionService;
import com.libmanagement.core.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

@RestController
@RequestMapping("/api/management") // decorator
public class BookTransactionController {
    private final BookService _bookService;
    private final StudentService _studentService;
    private final BookTransactionService _bookTransactionService;


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
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book Not Found");
        }

        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found");
        }
        _bookTransactionService.add(new BookTransaction("",bookId, studentId));
    }


    @GetMapping("/{studentId}")
    public List<Book> getAllBooksByStudentId(@PathVariable("studentId") String studentId) {
        var bookTransactions = _bookTransactionService.getByStudentId(studentId);
        List<Book> result = new ArrayList<>();
        for (var bookTransaction: bookTransactions) {
            result.add(_bookService.getById(bookTransaction.bookId));
        }
        return result;
    }


    @DeleteMapping("/return/{Id}")
    public void returnBook(@PathVariable String Id) {
        _bookTransactionService.returnBook(Id);
    }


    @GetMapping("/total/borrowed/{studentId}")
    public int getTotalBorrowedBooks(@PathVariable("studentId") String studentId) {
        var bookTransactions = _bookTransactionService.getByStudentId(studentId);
        return bookTransactions.size();       
    }

    @GetMapping("/total/borrowed/")
    //booktran
    public int getTotalBorrowedBooks() {
        var bookTransactions = _bookTransactionService.getAll();
        return bookTransactions.size();    
        //retuen booktra.returnTBookR;   
    }
//3
//fifet enđate ==null ->>dem 

    @GetMapping("/total")
    //bookser
    public int getTotalBooks() {
        int totalBooks = 0;
        List<Book> allBooks = _bookService.getAll();
        for(Book book: allBooks){
            totalBooks += book.Quantity; 
        }

        return totalBooks;
}}
//4
//xoa r -> k dem

//5
//test 1 file 
//tester import ser  

//6
//chuyen total ->ser

//dataS lấy lên
