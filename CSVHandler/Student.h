// Student.h
#ifndef STUDENT_H
#define STUDENT_H

#include <string>

class Student {
public:
    Student();
    Student(const std::string& studentID, const std::string& name, const std::string& className, const std::string& major);

    std::string getStudentID() const;
    void setStudentID(const std::string& studentID);

    std::string getName() const;
    void setName(const std::string& name);

    std::string getClassName() const;
    void setClassName(const std::string& className);

    std::string getMajor() const;
    void setMajor(const std::string& major);

private:
    std::string studentID;
    std::string name;
    std::string className;
    std::string major;
};

#endif
