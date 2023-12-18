package com.libmanagement.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public String Id;
    public String Name;
    public String Major;
    public String Clan;
    public boolean Deleted;

    public String password;
    public String username;
}
