package com.libmanagement.core.models;

public class BookTransaction {
    public String bookId;
    public String studentId;
    public String Id;

    public BookTransaction(String Id , String bookId, String studentId) {
        this.Id = Id;
        this.bookId = bookId;
        this.studentId = studentId;
        
    }
}