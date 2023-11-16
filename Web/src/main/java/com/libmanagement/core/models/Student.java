package com.libmanagement.core.models;


public class Student {
    public String Id;
    public String Name;
    public String Major;
    public String Clan;
    public boolean Deleted;

    public String password;
    public String username;
    
    public Student(String Id, String name, String clan, String major, boolean deleted ) {
        this.Id = Id;
        this.Name = name;
        this.Major = major;
        this.Clan = clan;     
        this.Deleted = deleted; 
    }

    public Student(String Id, String name, String clan, String major) {
        this.Id = Id;
        this.Name = name;
        this.Major = major;
        this.Clan = clan;     
        this.Deleted = false; 
    }
}