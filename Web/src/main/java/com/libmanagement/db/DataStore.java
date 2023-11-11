package com.libmanagement.db;
import java.util.Arrays;
import java.util.List;

import com.libmanagement.core.models.Book;
import com.libmanagement.core.models.BookTransaction;
import com.libmanagement.core.models.Student;
import com.libmanagement.utility.Utils;

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

    public static List<BookTransaction> bookTransactions = Arrays.asList(
        new BookTransaction(Utils.generateID() ,"CS110" , "A1002"),
        new BookTransaction(Utils.generateID(),"CS111", "A1004"),
        new BookTransaction(Utils.generateID(),"MA110", "A1003")
    );

}

