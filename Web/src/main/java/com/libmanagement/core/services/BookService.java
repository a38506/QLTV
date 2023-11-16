package com.libmanagement.core.services;

import java.text.MessageFormat;
import java.util.List;


import com.libmanagement.core.models.Book;
import com.libmanagement.db.DataStore;
import com.libmanagement.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public void add(Book bookToAdd){
        if (Utils.isNullOrEmpty(bookToAdd.Id)){
            throw new RuntimeException("Id Should Not Be Empty.");
        }

        if (Utils.isNullOrEmpty(bookToAdd.Name)){
            throw new RuntimeException("Name Should Not Be Empty.");
        }
        
        Book res = getById(bookToAdd.Id);
        if (res != null){
            //throw new RuntimeException(MessageFormat.format ("The book with Id {0} has already existed.",bookToAdd.ID));
            res.Quantity += 1;
            return;
        }
        bookToAdd.Quantity = 1;
        DataStore.books.add(bookToAdd);    
    }

    // public void add(List<Book> bookToAdd) {
    //     for (Book book : bookToAdd) {
    //         if (Utils.isNullOrEmpty(book.Id)) {
    //             throw new RuntimeException("Id Should Not Be Empty.");
    //         }
    
    //         if (Utils.isNullOrEmpty(book.Name)) {
    //             throw new RuntimeException("Name Should Not Be Empty.");
    //         }
    
    //         Book res = getById(book.Id);
    //         if (res != null) {
    //             res.Quantity += 1;
    //         } else {
    //             book.Quantity = 1;
    //             DataStore.books.add(book);
    //         }
    //     }
    // }
    

    public void removeById(String Id) {
        Book bookToRemove = getById(Id);
        if (bookToRemove != null) {
            if (!bookToRemove.Deleted) {
                bookToRemove.Deleted = true;
            } else {
                throw new RuntimeException(MessageFormat.format("Book with Id {0} is already deleted", Id));
            }
        } else {
            throw new RuntimeException(MessageFormat.format("Book with Id {0} not found", Id));
        }
    }
    

    // public void remove(String IDBook) {
    //     DataStore.books.removeIf(book -> book.ID.equals(IDBook));
    // }


    public  Book getById(String Id) {                          
        Book res = DataStore.books.stream().filter(book -> book.Id.equals(Id)).findFirst().orElse(null);
        return  res;
    }


    public List<Book> getAll() {
        return DataStore.books;
    }

    
    public void update(Book bookToUpdate) {
        Book res = getById(bookToUpdate.Id);
        if (res == null){
            throw new RuntimeException(MessageFormat.format ("Book With Id is {0} Not Found",bookToUpdate.Id));
        }
        res.Name = bookToUpdate.Name;
        res.Author =bookToUpdate.Author;
        res.Major = bookToUpdate.Major;
        return;         
    }

    //tổng sách không lấy sách xóa
    public int getTotalBooks() {
        int totalBooks = 0;
        List<Book> allBooks = getAll();
        for(Book book: allBooks){
            if ( !book.Deleted) {
                totalBooks += book.Quantity;
            } 
        }
        return totalBooks;
    }


}

        
        




