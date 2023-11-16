package com.libmanagement.core.services;

import java.text.MessageFormat;
import java.util.List;

import com.libmanagement.core.models.Student;
import com.libmanagement.db.DataStore;
import com.libmanagement.utility.Utils;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void add(Student studentToAdd) {
        if (Utils.isNullOrEmpty(studentToAdd.Id)){
            throw new RuntimeException("Id Should Not Be Empty.");
        }

        if (Utils.isNullOrEmpty(studentToAdd.Name)){
            throw new RuntimeException("Name Should Not Be Empty.");
        }

        Student res = getById(studentToAdd.Id);
        if (res != null){
            throw new RuntimeException(MessageFormat.format ("The stuent with Id {0} has already existed.",studentToAdd.Id));
        }
        DataStore.students.add(studentToAdd);
    }

    
    public void removeById(String Id) {
        Student studentToRemove = getById(Id);
        if (studentToRemove != null) {
            if (!studentToRemove.Deleted) {
                studentToRemove.Deleted = true;
            } else {
                throw new RuntimeException(MessageFormat.format("Student with Id {0} is already deleted", Id));
            }
        } else {
            throw new RuntimeException(MessageFormat.format("Student with Id {0} not found", Id));
        }
    }

    public  Student getById(String Id) {                                                                  
        Student res = DataStore.students.stream().filter(student -> student.Id.equals(Id)).findFirst().orElse(null);
        return  res;
    }


    public List<Student> getAll() {
        return DataStore.students;
    }


    public void update(Student studentToUpdate) {
        Student res = getById(studentToUpdate.Id);
        if (res == null){
            throw new RuntimeException(MessageFormat.format ("Student With Id is {0} Not Found",studentToUpdate.Id));
        }
        res.Name = studentToUpdate.Name;
        res.Clan = studentToUpdate.Clan;
        res.Major = studentToUpdate.Major;
        return;         
    }

    public boolean isDeleted(String studentId) {
        Student student = getById(studentId);
        return student != null && student.Deleted;
    }
}

