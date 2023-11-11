package com.libmanagement.web.controllers;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.services.BookService;
import com.libmanagement.core.services.ManagementService;
import com.libmanagement.core.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/management") // decorator
public class ManagementController {
    private final BookService _bookService;
    private final StudentService _studentService;
    private final ManagementService _managementService;

    public ManagementController(BookService bookService, StudentService studentService, ManagementService managementService) {
        _bookService = bookService;
        _studentService = studentService;
        _managementService = managementService;
    }

    //mượn sách
    @PostMapping()
    public void BorrowBook(String studentId, String bookId) {
        var book = _bookService.searchBooksByID(bookId);
        var student = _studentService.searchStudentsByID(studentId);

        if (book.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book Not Found");
        }

        if (student.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found");
        }

        _managementService.addManagement(bookId, book.get(0).getName_Book(), studentId, student.get(0).getName_Student());
    }

    //liet ke sách theo id sv
    @GetMapping("/{studentId}")
    public List<Book> getAllBooksByStudentId(@PathVariable("studentId") String studentId) {
        var managements = _managementService.getManagementsByStudentID(studentId);
        List<Book> result = new ArrayList<>();
        for (var management: managements) {
            result.add(_bookService.searchBooksByID(management.getManagerIDBook()).get(0));
        }
        return result;
    }

    //trả sách
    @DeleteMapping("/{studentId}/{bookId}")
    public void returnBook(@PathVariable String studentId, @PathVariable String bookId) {
        _managementService.returnBook(bookId, studentId);
    }

    //tổng sách sv mượn
    @GetMapping("/total-borrowed/{studentId}")
    public int getTotalBorrowedBooks(@PathVariable("studentId") String studentId) {
        var managements = _managementService.getManagementsByStudentID(studentId);
        return managements.size();       
    }

    //tổng sách trg kho
    @GetMapping("/total- in stock/{studentId}")
    public int getTotalBooksInStock(@PathVariable("studentId") String studentId) {

        List<Book> allBooks = _bookService.getAllBooks();

        List<Book> borrowedBooks = new ArrayList<>();
        var managements = _managementService.getManagementsByStudentID(studentId);
        for (var management : managements) {
            borrowedBooks.add(_bookService.searchBooksByID(management.getManagerIDBook()).get(0));
        }

        int totalBooksInStock = allBooks.size() - borrowedBooks.size();

        return totalBooksInStock;
}

    //xóa người dùng theo dòng
    @DeleteMapping("/delete/{studentId}/{bookId}")
    public void deleteManagement(@PathVariable String studentId, @PathVariable String bookId) {
        _managementService.removeManagement(bookId, studentId);
    }
}
