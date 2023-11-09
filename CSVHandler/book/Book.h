#ifndef STUDENT_H
#define STUDENT_H

#include <string>
#include <vector>

class Book {
public:
    Book();
    Book(const std::string& bookID, const std::string& name, const std::string& author, const std::string& major);

    std::string getBookID() const;
    std::string getName() const;
    std::string getAuthor() const;
    std::string getMajor() const;

    void setBookID(const std::string& bookID);
    void setName(const std::string& name);
    void setAuthor(const std::string& author);
    void setMajor(const std::string& major);

private:
    std::string bookID;
    std::string name;
    std::string author;
    std::string major;
};

#endif