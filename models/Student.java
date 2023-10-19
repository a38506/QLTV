
public class Student {
    private String ID_Student;
    private String Name_Student;
    private String Major_Student;
    private String Class_Student;

    public Student(String IDStudent, String nameStudent, String majorStudent, String classStudent ) {
        this.ID_Student = IDStudent;
        this.Name_Student = nameStudent;
        this.Major_Student = majorStudent;
        this.Class_Student = classStudent;
    }

    public String getID_Student() {
        return ID_Student;
    }

    public String getName_Student() {
        return Name_Student;
    }

    public String getMajor_Student() {
        return Major_Student;
    }

    public String getClass_Student(){
        return Class_Student;
    }
}