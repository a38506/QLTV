package com.libmanagement.db;
import java.util.Arrays;
import java.util.List;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.models.Management;
import com.libmanagement.core.models.Student;

public class DataStore{
    public static List<Student> students =  Arrays.asList (
        new Student (" A1002", "Nguyen Van A", "TTh3", "Toán_tin " ),
        new Student("A1003", "Nguyen Van B", "TTh4", "Toán_tin "),
        new Student("A1004", "Nguyen Van C", "TTh3", "CNTT"),
        new Student("A1035", "Nguyen Van D", "TTh3", "Toán_tin")  
    );

    public static List<Book> books = Arrays.asList(
        new Book("CS002", "Co so du lieu", "John", "Toan-Tin"),
        new Book("CS110", "Giai tich 1", "Thomas", "Toan-Tin"),
        new Book("CS111", "Giai tich 2", "Ed", "Toan-Tin"),
        new Book("MA110", "Kien truc may tinh", "Mask", "Toan_tin"),
        new Book("MA111", "Lap trinh huong doi tuong", "Alan", "CNTT"),
        new Book("IS111", "Mang may tinh", "Hon", "CNTT")
    );

    public static List<Management> managements = Arrays.asList(
        new Management("CS110","Giai tich 1" , "A1002", "Nguyen Van A"),
        new Management("CS111","Giai tich 2", "A1004", "Nguyen Van C"),
        new Management("MA110","Kien truc may tinh", "A1003", "Nguyen Van B")
    );
}

