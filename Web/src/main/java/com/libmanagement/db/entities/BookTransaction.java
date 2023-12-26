package com.libmanagement.db.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookTransaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public String bookId;
    public String studentId;
    public String Id;
    public LocalDateTime start;
    public LocalDateTime end;
}

