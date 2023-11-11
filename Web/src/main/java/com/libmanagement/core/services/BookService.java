package com.libmanagement.core.services;

import java.text.MessageFormat;
import java.util.List;

import com.libmanagement.core.models.Book;
import com.libmanagement.db.DataStore;
import com.libmanagement.utility.Utils;

public class BookService {

    public void add(Book bookToAdd){
        if (Utils.isNullOrEmpty(bookToAdd.ID)){
            throw new RuntimeException("Id Should Not Be Empty.");
        }

        if (Utils.isNullOrEmpty(bookToAdd.Name)){
            throw new RuntimeException("Name Should Not Be Empty.");
        }

        Book res = getById(bookToAdd.ID);
        if (res != null){
            throw new RuntimeException(MessageFormat.format ("The book with Id {0} has already existed.",bookToAdd.ID));
        }
        DataStore.books.add(bookToAdd);    
    }


    public void remove(String IDBook) {
        DataStore.books.removeIf(book -> book.ID.equals(IDBook));
    }


    public  Book getById(String ID) {
        //res                                      lamda expression                               
        Book res = DataStore.books.stream().filter(book -> book.ID.equals(ID)).findFirst().orElse(null);
        return  res;
    }


    public List<Book> getAll() {
        return DataStore.books;
    }

    
    public void update(Book bookToUpdate) {
        Book res = getById(bookToUpdate.ID);
        if (res == null){
            throw new RuntimeException(MessageFormat.format ("Book With Id is {0} Not Found",bookToUpdate.ID));
        }

        res.Name = bookToUpdate.Name;
        res.Author =bookToUpdate.Author;
        res.Major = bookToUpdate.Major;
        return; 
             
    }
}

        
        




