package com.libmanagement.core.models;


public class Book {
    public String Id;
    public String Name;
    public String Author;
    public String Major;
    public int Quantity;
    public boolean Deleted;

    public Book(String ID, String name, String author, String major, int quantity, boolean deleted) {
        this.Id = ID;
        this.Name = name;
        this.Author = author;
        this.Major = major;
        this.Quantity = quantity;
        this.Deleted = deleted;
    }  

    public Book(String ID, String name, String author, String major) {
        this.Id = ID;
        this.Name = name;
        this.Author = author;
        this.Major = major;
        this.Quantity = 1;
        this.Deleted = false;
        
    }

    
}
    
