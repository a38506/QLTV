package com.libmanagement.core.models;

public class Student {  
    public String ID;
    public String Name;
    public String Major;
    public String Clan;

    public String password;
    public String username;
    
    public Student(String ID, String name, String clan, String major ) {
        this.ID = ID;
        this.Name = name;
        this.Major = major;
        this.Clan = clan;      
    }
}