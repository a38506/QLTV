package com.libmanagement.db.repo;
import com.libmanagement.db.entities.Book;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Book, String> {

}
