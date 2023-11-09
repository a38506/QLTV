package com.libmanagement.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.libmanagement.core.models.Book;
import com.libmanagement.db.DataStore;

//trực tiếp data
//nạp ngược lại datta
public class BookService {

    public void addBook(String nameBook, String author, String major) {
        String IDBook = generateID(); 
        Book book = new Book(IDBook, nameBook, author, major);
        DataStore.books.add(book);
    }

    public void removeBook(String IDBook) {
        DataStore.books.removeIf(book -> book.getID_Book().equals(IDBook));
    }

    public List<Book> searchBooksByID(String ID) {
        List<Book> result = new ArrayList<>();
        for (Book book : DataStore.books) {
            if (book.getID_Book().equalsIgnoreCase(ID)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return DataStore.books;
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}

