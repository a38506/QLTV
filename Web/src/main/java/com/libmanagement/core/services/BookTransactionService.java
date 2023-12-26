package com.libmanagement.core.services;

import com.libmanagement.db.repo.BookRepository;
import com.libmanagement.db.repo.BookTransactionRepository;
import com.libmanagement.core.models.BookTransaction;
import com.libmanagement.db.DataStore;
import com.libmanagement.utility.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookTransactionService {

    @Autowired   
    private BookTransactionRepository bookTransactionRepository;

    public List<BookTransaction> getAll() {
        return DataStore.bookTransactions;
    }


    public List<BookTransaction> getByStudentId(String studentId) {                                                                   
        List <BookTransaction>  res = DataStore.bookTransactions.stream().filter(
                booktransaction -> booktransaction.studentId.equals(studentId)).toList();
        return res;
    }


    public List<BookTransaction> getByBookId(String bookId) {                                                                  
        List<BookTransaction> res = DataStore.bookTransactions.stream().filter(
                booktransaction -> booktransaction.bookId.equals(bookId)).toList();
        return  res;
    }

    
    public void add(BookTransaction borrowBook) {
        if (Utils.isNullOrEmpty(borrowBook.bookId)){
            throw new RuntimeException("Book With Id Should Not Be Empty.");
        }

        if (Utils.isNullOrEmpty(borrowBook.studentId)){
            throw new RuntimeException("Student With Id Should Not Be Empty.");
        }
        borrowBook.Id = Utils.generateID();
        DataStore.bookTransactions.add(borrowBook);
    }
       
    
    public void returnBook(String Id) {
        BookTransaction res = DataStore.bookTransactions.stream().filter(bookTransaction -> bookTransaction.Id.equals(Id)).findFirst().orElse(null);
        if(res == null){
            throw new RuntimeException(MessageFormat.format ("Transaction with Id {0} not found",Id));
        }

        // if (res.end != null) {
        //     throw new RuntimeException("This book has already been returned");
        // }
        res.end = LocalDateTime.now();                                                     
    }

    //tổng sách mượn lấy cả xóa
    public int getTotalBorrowedBooks() {
        List<BookTransaction> bookTransactions = getAll();
        return bookTransactions.size();
    }


    public  int getTotalBorrowingBooksCount() {
        List<BookTransaction> borrowedTransactions = new ArrayList<>();
        for (BookTransaction transaction : DataStore.bookTransactions) {
            if (transaction.end == null) {
                borrowedTransactions.add(transaction);
            }
        }

        return borrowedTransactions.size();
    }

}



