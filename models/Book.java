public class Book {
    private String ID_Book;
    private String Name_Book;
    private String _Author;
    private String _Major;

    public Book(String IDBook, String nameBook, String author, String major) {
        this.ID_Book = IDBook;
        this.Name_Book = nameBook;
        this._Author = author;
        this._Major = major;
    }

    public String getID_Book() {
        return ID_Book;
    }

    public String getName_Book() {
        return Name_Book;
    }

    public String get_Author() {
        return _Author;
    }

    public String get_Major() {
        return _Major;
    }
}
    
