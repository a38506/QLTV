import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String IDBook;
    private String nameBook;

    public void setBook(String IDBook, String nameBook) {
    this.IDBook = IDBook;
    this.nameBook = nameBook;
    }

    public String getIDBook() {
        return IDBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public static void main(String[] args) throws IOException {
    String csvFile1 = "book.csv";
    String line;
    String csvSplitBy = ",";
    
    List<Book> books = new ArrayList<>();

    BufferedReader br = new BufferedReader(new FileReader(csvFile1));
    while ((line = br.readLine()) != null) {
        String[] bookData = line.split(csvSplitBy);

        if (bookData.length >= 2) {
            String IDBook = bookData[0].trim();
            String nameBook = bookData[1].trim();

            Book book = new Book();
            book.setBook(IDBook, nameBook);
            books.add(book);
        }
    }
    br.close();

    for (Book book : books) {
        System.out.println("ID: " + book.getIDBook() + ", Name: " + book.getNameBook());
    }  
}
}