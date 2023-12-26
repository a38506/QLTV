package com.libmanagement.db.repo;
import com.libmanagement.db.entities.Student;
import org.springframework.data.repository.CrudRepository;
public interface StudentRepository extends CrudRepository<Student, String> {

}