package com.libmanagement.core.services;

import com.libmanagement.core.models.BookTransaction;
import com.libmanagement.db.DataStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class BookTransactionServiceTest {
    @InjectMocks
    private BookTransactionService bookTransactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<BookTransaction> result = bookTransactionService.getAll();
        assertEquals(DataStore.bookTransactions.size(), result.size());
    }

   @Test
void testGetByStudentId() {
    List<BookTransaction> bookTransactions = DataStore.bookTransactions;
    BookTransaction result = bookTransactions.get(0);
    String studentIdToSearch = "A1002";

    // Thực hiện gọi phương thức và kiểm tra kết quả
    assertDoesNotThrow(() -> {
        List<BookTransaction> resultTransactions = bookTransactionService.getByStudentId(studentIdToSearch);
        
        // Kiểm tra xem kết quả có phải là danh sách không null
        assertNotNull(resultTransactions,  "null");

        // Kiểm tra xem có đúng một phần tử trong danh sách hay không
        assertEquals(1, resultTransactions.size(), "True");

        // Lấy giao dịch từ kết quả
        BookTransaction resultTransaction = resultTransactions.get(0);

        // Kiểm tra thông tin giao dịch
        assertEquals(studentIdToSearch, resultTransaction.studentId, "StudentId should match");
    });
}


    // @Test
    // void testGetByStudentId() {
    //     List<BookTransaction> bookTransactions = DataStore.bookTransactions;
    //     String studentIdToSearch = "A1002";

    //     List<BookTransaction> result = bookTransactionService.getByStudentId(studentIdToSearch);

    //     result.forEach(transaction ->
    //         assertEquals(studentIdToSearch, transaction.studentId, "StudentId should match for each transaction")
    //     );
    // }



    @Test
    void testGetByBookId() {
        List<BookTransaction> result = bookTransactionService.getByBookId("CS110");
        assertEquals(2, result.size());
        assertEquals("CS110", result.get(0).bookId);
        assertEquals("CS110", result.get(1).bookId);
    }

    @Test
    void testAdd() {
        List<BookTransaction> transactions = DataStore.bookTransactions;
        BookTransaction res = transactions.get(0);
        BookTransaction transactionToAdd = new BookTransaction(res.Id, "newId", "newId");

        assertDoesNotThrow(() -> bookTransactionService.add(transactionToAdd));

        BookTransaction addTransaction = bookTransactionService.getAll().get(transactions.size());
        assertEquals(res.bookId, addTransaction.bookId);
        assertEquals(res.studentId , addTransaction.studentId);
    }

    @Test
    void testReturnBook() {
        List<BookTransaction> transactions = DataStore.bookTransactions;
        BookTransaction res = transactions.get(0);

        assertDoesNotThrow(() -> bookTransactionService.returnBook(res.Id));

        BookTransaction returnedTransaction = bookTransactionService.getAll().get(0);
        assertNotNull(returnedTransaction.end);
    }

    @Test
    void testGetTotalBorrowedBooks() {
        int expectedTotal = DataStore.bookTransactions.size();
        int totalBooks = bookTransactionService.getTotalBorrowedBooks();
        assertEquals(expectedTotal, totalBooks);
    }
}
