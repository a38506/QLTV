package com.libmanagement.web.controllers;

import java.util.List;
import java.util.Optional;

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
        return _studentService.getAllStudents();
    }

    @PostMapping()
    public void addNewStudent(Student student){
        _studentService.addStudent(student.getName_Student(), student.getMajor_Student(), student.getClass_Student());
    }

    @DeleteMapping()
    public void deleteStudent(Student student){
        _studentService.removeStudent(student.getID_Student());
    }    

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable String studentId, @RequestBody Student updatedStudent){
        Optional<Student> existingStudent = _studentService.searchStudentsByID(studentId).stream().findAny();

        if (existingStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
        }

        else {
            Student studentToUpdate = existingStudent.get();
            studentToUpdate.setName_Student(updatedStudent.getName_Student());
            studentToUpdate.setMajor_Student(updatedStudent.getMajor_Student());
            studentToUpdate.setClass_Student(updatedStudent.getClass_Student());

            _studentService.updateStudent(studentId, studentToUpdate.getName_Student(), studentToUpdate.getMajor_Student(), studentToUpdate.getClass_Student());
        }
    }


    
}
