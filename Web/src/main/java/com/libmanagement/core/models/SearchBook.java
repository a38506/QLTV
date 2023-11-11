package com.libmanagement.core.models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SearchBook {
    public static void main(String[] args) throws IOException {
        String csvFile = "book.csv";
        String line;
        String csvSplitBy = ",";
        
        List<Book> sachs = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            String[] sachData = line.split(csvSplitBy);
            
            if (sachData.length >= 4) {
                String IDSach = sachData[0].trim();
                String tenSach = sachData[1].trim();
                String tacGia = sachData[2].trim();
                String chuyenNganh = sachData[3].trim();
                
                Book sach = new Book(IDSach, tenSach, tacGia, chuyenNganh);
                sachs.add(sach);
            }
        }
        br.close();
            
        System.out.print("Nhap ID sach can tim kiem: ");
        Scanner scanner = new Scanner(System.in);
        String searchID = scanner.nextLine();
        
        boolean found = false;
        System.out.println("Ket qua tim kiem:");
        System.out.format("%-20s %-20s %-20s %-20s\n", "ID sach", "Ten sach", "Tac gia", "Chuyen nganh");
        
        for (Book sach : sachs) {
            if (sach.getID_Book().equals(searchID)) {
                System.out.format("%-20s %-20s %-20s %-20s\n", sach.getID_Book(), sach.getName_Book(),
                        sach.get_Author(), sach.get_Major());
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("\nKhong tim thay ID sach da nhap.");
        }
        
        scanner.close();
    }
}

//nên ở services