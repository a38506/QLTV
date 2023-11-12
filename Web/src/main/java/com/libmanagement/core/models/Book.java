package com.libmanagement.core.models;

public class Book {
    public String ID;
    public String Name;
    public String Author;
    public String Major;
    public int Quantity;

    public Book(String ID, String name, String author, String major, int quantity) {
        this.ID = ID;
        this.Name = name;
        this.Author = author;
        this.Major = major;
        this.Quantity = quantity;
    }  

    public Book(String ID, String name, String author, String major) {
        this.ID = ID;
        this.Name = name;
        this.Author = author;
        this.Major = major;
        this.Quantity = 1;
        
    }  
}
    
