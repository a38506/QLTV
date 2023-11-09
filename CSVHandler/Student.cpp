// Student.cpp
#include "Student.h"

Student::Student() : studentID(""), name(""), className(""), major("") {
}

Student::Student(const std::string& studentID, const std::string& name, const std::string& className, const std::string& major)
    : studentID(studentID), name(name), className(className), major(major) {
}

std::string Student::getStudentID() const {
    return studentID;
}

void Student::setStudentID(const std::string& studentID) {
    this->studentID = studentID;
}

std::string Student::getName() const {
    return name;
}

void Student::setName(const std::string& name) {
    this->name = name;
}

std::string Student::getClassName() const {
    return className;
}

void Student::setClassName(const std::string& className) {
    this->className = className;
}

std::string Student::getMajor() const {
    return major;
}

void Student::setMajor(const std::string& major) {
    this->major = major;
}
