package com.libmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService _studentService ;

    @Autowired
    StudentController(StudentService studentService){
        _studentService = studentService;
    }

//    @GetMapping()
//    public List<Student> getAll() {
//        return DataStore.students.stream().filter(student-> !student.Deleted).collect (Collectors.toList( ));
//    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return _studentService.getAll();
    }

    @PostMapping()
    public void addNewStudent(Student student){
        try {
        _studentService.add(student);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/remove/{Id}")
        public void remove(@PathVariable String Id) {
            try {    
                _studentService.removeById(Id);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }   

    @PutMapping("/{studentId}")
    public void update(@PathVariable String studentId, @RequestBody Student studentToUpdate) {
        try {
            _studentService.update(studentToUpdate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
