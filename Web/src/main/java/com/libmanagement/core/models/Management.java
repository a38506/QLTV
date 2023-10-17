import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) throws IOException {
        String csvFile = "bookManagement.csv";
        String line;
        String csvSplitBy = ",";

        List<Management> managers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            String[] managerData = line.split(csvSplitBy);

            if (managerData.length >= 2) {
                String IDBook = managerData[0].trim();
                String IDStudent = managerData[1].trim();

                Management manager = new Management(IDBook, IDStudent);
                managers.add(manager);
            }
        }
        br.close();

        for (Management manager : managers) {
            System.out.println("IDBook: " + manager.getManagerIDBook() + ", IDStudent: " + manager.getManagerIDStudent());
        }
    }
}