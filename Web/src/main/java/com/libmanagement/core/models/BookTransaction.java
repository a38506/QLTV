package com.libmanagement.core.models;

import java.time.LocalDateTime;

public class BookTransaction {
    public String bookId;
    public String studentId;
    public String Id;
    public LocalDateTime start;
    public LocalDateTime end;

    public BookTransaction(String Id , String bookId, String studentId,  LocalDateTime start , LocalDateTime end ) {
        this.Id = Id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.start = start;
        this.end = end;   
    }

    public BookTransaction(String Id , String bookId, String studentId, LocalDateTime start ) {
        this.Id = Id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.start = start;
        this.end = null;     
    }

    public BookTransaction(String Id , String bookId, String studentId) {
        this.Id = Id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.start = LocalDateTime.now();
        this.end = null;   
    }
}