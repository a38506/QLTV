#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
#include <stdexcept>
#include "Student.h"

// Lớp template CSVHandler
template <typename T>
class CSVHandler {
private:
    std::string directory_;
    std::vector<std::string> fields_;

public:
    CSVHandler(const std::string& dir) : directory_(dir) {}

    CSVHandler<T>& addColumn(const std::string& colName) {
        fields_.push_back(colName);
        return *this;
    }

    std::vector<T> read() {
        std::ifstream file(directory_);
        if (!file.is_open()) {
            throw std::runtime_error("Khong the mo tep tin de doc.");
        }

        std::string line;
        std::getline(file, line);

        std::vector<T> data;
        while (std::getline(file, line)) {
            std::istringstream ss(line);
            T item;

            for (const std::string& colName : fields_) {
                std::string token;
                std::getline(ss, token, ',');
                if (colName == "ID") item.setStudentID(token);
                if (colName == "Name") item.setName(token);
                if (colName == "Class") item.setClassName(token);
                if (colName == "Major") item.setMajor(token);
               
            }

            data.push_back(item);
        }

        file.close();
        return data;
    }


    void write(const std::vector<T>& items) {
        std::ofstream file(directory_, std::ios_base::app);
        if (!file.is_open()) {
            throw std::runtime_error("Khong the mo tep tin de ghi.");
        }

        for (const T& item : items) {
            std::ostringstream ss;
            for (const std::string& colName : fields_) {
                if (colName == "ID") ss << "A" << item.getStudentID();
                if (colName == "Name") ss << item.getName();
                if (colName == "Class") ss << item.getClassName();
                if (colName == "Major") ss << item.getMajor();
                
                ss << ',';
            }

            std::string line = ss.str();
            file << line.substr(1) << std::endl;
        }

        file.close();
    }

};

int main() {
    std::string choice;
    std::cout << "Chon lua chuc nang (write/read): ";
    std::cin >> choice;

    if (choice == "read"){
        CSVHandler<Student> csv("students.csv");
        csv.addColumn("ID").addColumn("Name").addColumn("Class").addColumn("Major"); 
        
        std::vector<Student> student_read = csv.read();


        std::cout << "ID  " << "Name  " << "Class  " << "Major" <<std::endl;
        for (const Student& student : student_read) {
            std::cout << student.getStudentID()<<", " << student.getName()<<", " << student.getClassName()<<", "<< student.getMajor()<<", " << std::endl;
        }
    }

    if (choice == "write"){
        CSVHandler<Student> csv("students.csv");
        csv.addColumn("ID").addColumn("Name").addColumn("Class").addColumn("Major");

        std::vector<Student> student_wirte;

        //st1
        Student st1;
        st1.setStudentID("1005");
        st1.setName("Nguyen Van E");
        st1.setClassName("TTh3");
        st1.setMajor("Toan_tin");
        student_wirte.push_back(st1);

        //st2
        Student st2;
        st2.setStudentID("1000");
        st2.setName("Nguyen Van A");
        st2.setClassName("TTh4");
        st2.setMajor("Toan_tin");
        student_wirte.push_back(st2);

        // Ghi toàn bộ danh sách sinh viên vào tệp CSV
        csv.write(student_wirte);

        std::cout << "Du lieu da dc luu vao file.";
    }
    return 0;
}
