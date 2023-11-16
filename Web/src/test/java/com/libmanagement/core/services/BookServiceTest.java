package com.libmanagement.core.services;

import com.libmanagement.core.models.Book;
import com.libmanagement.db.DataStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        List<Book> books = DataStore.books;
        Book res = books.get(0);
        Book bookToAdd = new Book(res.Id, "New Book", "New Author","cntt");
        int initialQuantity = res.Quantity;

        assertDoesNotThrow(() -> bookService.add(bookToAdd));

        Book updatedBook = bookService.getById(res.Id);
        assertEquals( res.Name, updatedBook.Name);
        assertEquals(res.Author, updatedBook.Author);
        assertEquals(res.Major,  updatedBook.Major);
        assertEquals(initialQuantity + 1, updatedBook.Quantity);
    }

    @Test
    void testRemoveById() {
        List<Book> books = DataStore.books;
        Book bookToRemove = books.get(2);
        String bookId = bookToRemove.Id;

        assertDoesNotThrow(() -> bookService.removeById(bookId));
        Book removedBook = bookService.getById(bookId);
        assertTrue(removedBook.Deleted);
    }

    @Test
    void testUpdate() {
        List<Book> books = DataStore.books;
        Book bookToUpdate= books.get(0);
        String updatedName ="Updated Name";
        String updatedAuthor = "Updated  author";

        assertDoesNotThrow(() -> {
            bookToUpdate.Name=(updatedName);
            bookToUpdate.Author=(updatedAuthor);
            bookService.update(bookToUpdate);
        });

        Book updatedBook = bookService.getById(bookToUpdate.Id);
        assertEquals(updatedName, updatedBook.Name);
        assertEquals(updatedAuthor, updatedBook.Author);
    }

    @Test
    void testGetTotalBooks() {
        int expectedTotal = DataStore.books.stream().filter(book->!book.Deleted).mapToInt(book -> book.Quantity).sum();
        int totalBooks = bookService.getTotalBooks();
        assertEquals(expectedTotal, totalBooks);
    }

}
