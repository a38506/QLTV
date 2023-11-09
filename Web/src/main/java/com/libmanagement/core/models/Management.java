package com.libmanagement.core.models;

public class Management {
    private String managerIDBook;
    private String managerNameBook;
    private String managerIDStudent;
    private String managerNameStudent;

    public Management(String IDBook, String NameBook, String IDStudent, String NameStudent) {
        this.managerIDBook = IDBook;
        this.managerNameBook = NameBook;
        this.managerIDStudent = IDStudent;
        this.managerNameStudent = NameStudent;
    }

    public String getManagerIDBook() {
        return managerIDBook;
    }

    public String getManagerNameBook() {
        return managerNameBook;
    }

    public String getManagerIDStudent() {
        return managerIDStudent;
    }

    public String getManagerNameStudent() {
        return managerNameStudent;
    }

}