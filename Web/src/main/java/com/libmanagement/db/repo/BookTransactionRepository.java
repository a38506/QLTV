package com.libmanagement.db.repo;
import com.libmanagement.db.entities.BookTransaction;
import org.springframework.data.repository.CrudRepository;
public interface BookTransactionRepository extends CrudRepository<BookTransaction, String> {

}