package com.libmanagement.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public String Id;
    public String Name;
    public String Author;
    public String Major;
    public int Quantity;
    public boolean Deleted;
}
