package com.libmanagement.core.services;

import com.libmanagement.core.models.Student;
import com.libmanagement.db.DataStore;
import com.libmanagement.utility.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        List<Student> students = DataStore.students;
        Student res = students.get(0);
        Student StudentToAdd = new Student(Utils.generateID(), "New name", "New clan","cntt");
        System.out.println(StudentToAdd.Id);

        assertDoesNotThrow(() -> studentService.add(StudentToAdd));

        Student updatedStudent = studentService.getById(res.Id);
        assertEquals( res.Name, updatedStudent.Name);
        assertEquals(res.Clan, updatedStudent.Clan);
        assertEquals(res.Major,  updatedStudent.Major);
    }

    @Test
    void testRemoveById() {
        List<Student> students = DataStore.students;
        Student studentToRemove = students.get(2);
        String studentId = studentToRemove.Id;

        assertDoesNotThrow(() -> studentService.removeById(studentId));
        Student removedStudent = studentService.getById(studentId);
        assertTrue(removedStudent.Deleted);
        studentToRemove.Deleted = false;
    }
//viet bien chep du lieu cua data -> lm vc rieng tren bien do (deepcpy)
//moi test lam tren 1 bọ du lieu moi
    @Test
    void testUpdate() {
        List<Student> students = DataStore.students;
        Student studentToUpdate= students.get(0);
        String updatedName ="Updated Name";
        String updatedClan = "Updated  clan";

        assertDoesNotThrow(() -> {
            studentToUpdate.Name= updatedName;
            studentToUpdate.Clan= updatedClan;
            studentService.update(studentToUpdate);
        });

        Student updatedStudent = studentService.getById(studentToUpdate.Id);
        assertEquals(updatedName, updatedStudent.Name);
        assertEquals(updatedClan, updatedStudent.Clan);
    }

    @Test
    void testIsDeleted(){
        List<Student> students = DataStore.students;
        Student studentToRemove = students.get(2);
        String studentId = studentToRemove.Id;
        

        assertDoesNotThrow(() -> studentService.removeById(studentId));
        
        assertTrue(studentToRemove.Deleted);

        
    }

    
}
