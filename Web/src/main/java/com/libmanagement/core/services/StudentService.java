package com.libmanagement.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.libmanagement.core.models.Student;
import com.libmanagement.db.DataStore;

public class StudentService {

    public void addStudent(String nameStudent, String majorStudent, String classStudent) {
        String IDStudent = generateID(); 
        Student student = new Student(IDStudent, nameStudent, majorStudent, classStudent);
        DataStore.students.add(student);
    }

    public void removeStudent(String IDStudent) {
        DataStore.students.removeIf(student -> student.getID_Student().equals(IDStudent));
    }

    public List<Student> searchStudentsByID(String ID) {
        List<Student> result = new ArrayList<>();
        for (Student student : DataStore.students) {
            if (student.getID_Student().equalsIgnoreCase(ID)) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> getAllStudents() {
        return DataStore.students;
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}
