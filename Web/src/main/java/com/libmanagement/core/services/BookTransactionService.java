package com.libmanagement.core.services;

import com.libmanagement.core.models.BookTransaction;
import com.libmanagement.db.DataStore;
import com.libmanagement.utility.Utils;

import java.util.List;

public class BookTransactionService {

    public List<BookTransaction> getAll() {
        return DataStore.bookTransactions;
    }

    public BookTransaction getByStudentId(String studentId) {                                                                  
        BookTransaction res = DataStore.bookTransactions.stream().filter(
                booktransaction -> booktransaction.studentId.equals(studentId)).findFirst().orElse(null);
        return  res;
    }


    public BookTransaction getByBookId(String bookId) {                                                                  
        BookTransaction res = DataStore.bookTransactions.stream().filter(
                booktransaction -> booktransaction.bookId.equals(bookId)).findFirst().orElse(null);
        return  res;
    }

    
    public void add(BookTransaction borrowBook) {
        if (Utils.isNullOrEmpty(borrowBook.bookId)){
            throw new RuntimeException("Book With Id Should Not Be Empty.");
        }

        if (Utils.isNullOrEmpty(borrowBook.studentId)){
            throw new RuntimeException("Studenr With Id Should Not Be Empty.");
        }

        DataStore.bookTransactions.add(borrowBook);
    }
       
    

    public void remove(String bookID, String studentID) {
        DataStore.bookTransactions.removeIf(bookTransaction ->bookTransaction.bookId.equals(bookID) && bookTransaction.studentId.equals(studentID));
    }
}



