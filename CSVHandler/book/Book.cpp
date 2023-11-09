// Book.cpp
#include "Book.h"

Book::Book() : bookID(0), name(""), author(""), major("") {
}

Book::Book(const std::string& bookID, const std::string& name, const std::string& author, const std::string& major)
    : bookID(bookID), name(name), author(author), major(major) {
}

std::string Book::getBookID() const {
    return bookID;
}

std::string Book::getName() const {
    return name;
}

std::string Book::getAuthor() const {
    return author;
}

std::string Book::getMajor() const {
    return major;
}

void Book::setBookID(const std::string& bookID) {
    this->bookID = bookID;
}

void Book::setName(const std::string& name) {
    this->name = name;
}

void Book::setAuthor(const std::string& author) {
    this->author = author;
}

void Book::setMajor(const std::string& major) {
    this->major = major;
}
