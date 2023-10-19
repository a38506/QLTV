
public class Management {
    private String managerIDBook;
    private String managerIDStudent;

    public Management(String IDBook, String IDStudent) {
        this.managerIDBook = IDBook;
        this.managerIDStudent = IDStudent;
    }

    public String getManagerIDBook() {
        return managerIDBook;
    }

    public String getManagerIDStudent() {
        return managerIDStudent;
    }

}