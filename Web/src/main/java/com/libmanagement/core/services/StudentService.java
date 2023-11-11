package com.libmanagement.core.services;

import java.text.MessageFormat;
import java.util.List;

import com.libmanagement.core.models.Student;
import com.libmanagement.db.DataStore;
import com.libmanagement.utility.Utils;

public class StudentService {

    public void add(Student studentToAdd) {
        if (Utils.isNullOrEmpty(studentToAdd.ID)){
            throw new RuntimeException("Id Should Not Be Empty.");
        }

        if (Utils.isNullOrEmpty(studentToAdd.Name)){
            throw new RuntimeException("Name Should Not Be Empty.");
        }

        Student res = getById(studentToAdd.ID);
        if (res != null){
            throw new RuntimeException(MessageFormat.format ("The stuent with Id {0} has already existed.",studentToAdd.ID));
        }
        DataStore.students.add(studentToAdd);
    }

    
    public void remove(String ID) {
        DataStore.students.removeIf(student -> student.ID.equals(ID));
    }


    public  Student getById(String ID) {                                                                  
        Student res = DataStore.students.stream().filter(student -> student.ID.equals(ID)).findFirst().orElse(null);
        return  res;
    }


    public List<Student> getAll() {
        return DataStore.students;
    }


    public void update(Student studentToUpdate) {
        Student res = getById(studentToUpdate.ID);
        if (res == null){
            throw new RuntimeException(MessageFormat.format ("Student With Id is {0} Not Found",studentToUpdate.ID));
        }

        res.Name = studentToUpdate.Name;
        res.Clan = studentToUpdate.Clan;
        res.Major = studentToUpdate.Major;
        return;         
    }
}
