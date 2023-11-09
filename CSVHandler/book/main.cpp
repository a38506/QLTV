#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <algorithm>
#include <sstream>
#include "Student.h"

void writeToCSV(const std::string& filename, const std::vector<Student>& students) {
    std::ofstream outputFile(filename);
    
    if (outputFile.is_open()) {
        outputFile << "Student ID,Name,Class,Major\n";
        
        for (const Student& student : students) {
            outputFile << student.getStudentID() << ","
                       << student.getName() << ","
                       << student.getClassName() << ","
                       << student.getMajor() << "\n";
        }
        
        outputFile.close();
    } else {
        std::cerr << "Khong the mo tep tin de ghi." << std::endl;
    }
}

void readFromCSV(const std::string& filename, std::vector<Student>& students) {
    std::ifstream inputFile(filename);
    
    if (!inputFile.is_open()) {
        std::cerr << "Khong the mo tep tin de doc." << std::endl;
        return;
    }
    
    std::string line;
    while (getline(inputFile, line)) {
        std::istringstream ss(line);
        std::string token;
        
        std::string studentID, name, className, major;
        
        getline(ss, token, ',');
        studentID = token;
        
        getline(ss, token, ',');
        name = token;
        
        getline(ss, token, ',');
        className = token;
        
        getline(ss, token, ',');
        major = token;
        
        students.emplace_back(studentID, name, className, major);
    }
    
    inputFile.close();
}

void delete(std::vector<Student>& students, const std::string& studentID) {
    students.erase(std::remove_if(students.begin(), students.end(),
        [studentID](const Student& student) { return student.getStudentID() == studentID; }),
        students.end());
}

void update(std::vector<Student>& students, const std::string& studentID, const std::string& newName, const std::string& newClassName, const std::string& newMajor) {
    for (Student& student : students) {
        if (student.getStudentID() == studentID) {
            student.setName(newName);
            student.setClassName(newClassName);
            student.setMajor(newMajor);
        }
    }
}

int main() {
    const std::string filename = "students.csv";
    std::vector<Student> students;
    
    readFromCSV(filename, students);
    
    int choice;
    do {
        std::cout << "Chon tac vu:\n";
        std::cout << "1. Hien thi danh sach sinh vien\n";
        std::cout << "2. Them sinh vien\n";
        std::cout << "3. Xoa sinh vien\n";
        std::cout << "4. Sua thong tin sinh vien\n";
        std::cout << "0. Thoat\n";
        std::cin >> choice;
        
        switch (choice) {
            case 1:{
            
                for (const Student& student : students) {
                    std::cout << "Ma sinh vien: " << student.getStudentID() << ", Ten: " << student.getName() << ", Lop: " << student.getClassName() << ", Chuyen nganh: " << student.getMajor() << "\n";
                }
                break;
            }

            case 2: {
                std::string studentID, name, className, major;              
                std::cout << "Nhap ma sinh vien: ";
                std::cin >> studentID;
                std::cin.ignore();
                std::cout << "Nhap ten: ";
                std::getline(std::cin, name);
                std::cout << "Nhap lop: ";
                std::getline(std::cin, className);               
                std::cout << "Nhap chuyen nganh: ";
                std::getline(std::cin, major);
                
                students.emplace_back(studentID, name, className, major);
                break;
            }

            case 3: {
                std::string studentIDToDelete;
                std::cout << "Nhap ma sinh vien can xoa: ";
                std::cin >> studentIDToDelete;
                delete(students, studentIDToDelete);
                break;
            }

            case 4: {
                std::string studentIDToUpdate, newName, newClassName, newMajor;
                std::cout << "Nhap ma sinh vien can sua: ";
                std::cin >> studentIDToUpdate;
                std::cin.ignore();
                
                std::cout << "Nhap ten moi: ";
                std::getline(std::cin, newName);
                
                std::cout << "Nhap lop moi: ";
                std::getline(std::cin, newClassName);
                
                std::cout << "Nhap chuyen nganh moi: ";
                std::getline(std::cin, newMajor);
                
                update(students, studentIDToUpdate, newName, newClassName, newMajor);
                break;
            }


        }
    } while (choice != 0);
    
    writeToCSV(filename, students);
    
    std::cout << "Du lieu da duoc ghi vao tep tin" << std::endl;
    
    return 0;
}
