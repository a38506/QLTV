import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String IDStudent;
    private String nameStudent;

    public Student(String studentID, String studentName) {
        this.IDStudent = studentID;
        this.nameStudent = studentName;
    }

    public String getIDStudent() {
        return IDStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public static void main(String[] args) throws IOException {
        String csvFile = "student.csv";
        String line;
        String csvSplitBy = ",";

        List<Student> students = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            String[] studentData = line.split(csvSplitBy);

            if (studentData.length >= 2) {
                String studentID = studentData[0].trim();
                String studentName = studentData[1].trim();

                Student student = new Student(studentID, studentName);
                students.add(student);
            }
        }
        br.close();

        for (Student student : students) {
            System.out.println("ID: " + student.getIDStudent() + ", Name: " + student.getNameStudent());
        }
    }
}