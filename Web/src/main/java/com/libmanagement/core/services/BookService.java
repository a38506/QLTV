package com.libmanagement.core.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.libmanagement.core.models.Book;
import com.libmanagement.db.DataStore;
import com.libmanagement.db.repo.BookRepository;
import com.libmanagement.utility.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired   
    private BookRepository bookRepository;

    public void add(Book bookToAdd) {
        if (Utils.isNullOrEmpty(bookToAdd.Id)) {
            throw new RuntimeException("Id Should Not Be Empty.");
        }
    
        if (Utils.isNullOrEmpty(bookToAdd.Name)) {
            throw new RuntimeException("Name Should Not Be Empty.");
        }
    
        var query = bookRepository.findById(bookToAdd.Id);
        if (query.isPresent()) {
            var entity = query.get();
            entity.Quantity += 1;
            bookRepository.save(entity);
        } else {
            bookToAdd.Quantity = 1;
            bookRepository.save(bookToAdd);
        }
    }

    

    public void removeById(String Id) {
        var query = bookRepository.findById(Id);
        var entity = query.get();
        if (entity != null) {
            if (!entity.isDeleted()) {
                entity.Deleted = true;
                bookRepository.save(entity);
            } else {
                throw new RuntimeException(MessageFormat.format("Book with Id {0} is already deleted", Id));
            }
        } else {
            throw new RuntimeException(MessageFormat.format("Book with Id {0} not found", Id));
        }
    }
    


    public Book getById(String Id) {
        var query = bookRepository.findById(Id);
        if (query.isEmpty()) {
            return null;
        }
        var entity = query.get();
        return  new Book(entity.Id, entity.Name, entity.Author, entity.Major, entity.Quantity, entity.Deleted);
    }


    // public List<Book> getAll() {
    //     var query = bookRepository.findAll();
    //     var entity = query.get();
    //     return entity.stream()
    //             .filter(entity -> !entity.Deleted)
    //             .map(entity -> new Book(entity.Id, entity.Name, entity.Author, entity.Major, entity.Quantity, entity.Deleted)).toList();
    // }

    public List<Book> getAll() {
    var entities = bookRepository.findAll();
    List<Book> result = new ArrayList<>();

    for (Book entity : entities) {
        if (!entity.Deleted) {
            result.add(new Book(entity.Id, entity.Name, entity.Author, entity.Major, entity.Quantity, entity.Deleted));
        }
    }

    return result;
    }

    

    
    public void update(Book bookToUpdate) {
        var query = bookRepository.findById(bookToUpdate.Id);
        if (query == null){
            throw new RuntimeException(MessageFormat.format ("Book With Id is {0} Not Found", bookToUpdate.Id));
        }
        var entity = query.get();
        entity.Name = bookToUpdate.Name;
        entity.Author = bookToUpdate.Author;
        entity.Major = bookToUpdate.Major;
        return;    
    }


    //tổng sách không lấy sách xóa
    // public int getTotalBooks() {
    //     int totalBooks = 0;
    //     List<Book> allBooks = getAll();
    //     for(Book book: allBooks){
    //         if ( !book.Deleted) {
    //             totalBooks += book.Quantity;
    //         } 
    //     }
    //     return totalBooks;
    // }
    
    public int getTotalBooks() {
        var allBooks = bookRepository.findAll();
        int totalBooks = 0;
    
        for (Book entity : allBooks) {
            if (!entity.isDeleted()) {
                totalBooks += entity.Quantity;
            }
        }
    
        return totalBooks;
    }
}