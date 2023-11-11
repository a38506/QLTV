package com.libmanagement.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.libmanagement.core.models.Student;
import com.libmanagement.core.services.StudentService;

@RestController
@RequestMapping("/api/student") // decorator
public class StudentController {
    private final StudentService _studentService ;

    StudentController(StudentService studentService){
        _studentService = studentService;
    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return _studentService.getAll();
    }

    @PostMapping()
    public void addNewStudent(Student student){
        _studentService.add(student);
    }

    @DeleteMapping()
    public void removeStudent(Student student){
        _studentService.remove(student.ID);
    }    

    @PutMapping("/{studentId}")
    public void update(@PathVariable String studentId, @RequestBody Student studentToUpdate) {
        Student res = _studentService.getById(studentId);
        if (res == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
 //           throw new RuntimeException(MessageFormat.format ("Book With Id is {0} Not Found",bookToUpdate.ID));
        }

        res.Name = studentToUpdate.Name;
        res.Major =studentToUpdate.Major;
        res.Clan = studentToUpdate.Clan;
        return;
    }



}
