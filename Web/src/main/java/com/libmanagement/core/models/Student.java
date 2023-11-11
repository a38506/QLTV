package com.libmanagement.core.models;

public class Student {
    private String ID_Student;
    private String Name_Student;
    private String Major_Student;
    private String Class_Student;
    private String password;
    private String username;
    
    public Student(String IDStudent, String nameStudent, String majorStudent, String classStudent ) {
        this.ID_Student = IDStudent;
        this.Name_Student = nameStudent;
        this.Major_Student = majorStudent;
        this.Class_Student = classStudent;
        
    }

    public String getID_Student() {
        return ID_Student;
    }

    public String getName_Student() {
        return Name_Student;
    }

    public String getMajor_Student() {
        return Major_Student;
    }

    public String getClass_Student(){
        return Class_Student;
    }

    public void setName_Student(String nameStudent) {
        this.Name_Student = nameStudent;
    }

    public void setMajor_Student(String majorStudent) {
        this.Major_Student = majorStudent;
    }

    public void setClass_Student(String classStudent) {
        this.Class_Student = classStudent;
    }
    
    


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}