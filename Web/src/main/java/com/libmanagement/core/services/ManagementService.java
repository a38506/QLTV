package com.libmanagement.core.services;
import com.libmanagement.core.models.Management;
import com.libmanagement.db.DataStore;
import java.util.ArrayList;
import java.util.List;

public class ManagementService {

    public List<Management> getManagements() {
        return DataStore.managements;
    }

    public List<Management> getManagementsByStudentID(String studentID) {
        List<Management> result = new ArrayList<>();
        for (Management management : DataStore.managements) {
            if (management.getManagerIDBook().equals(studentID)) {
                result.add(management);
            }
        }
        return result;
    }

    public List<Management> getManagementsByBookID(String bookID) {
        List<Management> result = new ArrayList<>();
        for (Management management : DataStore.managements) {
            if (management.getManagerIDStudent().equals(bookID)) {
                result.add(management);
            }
        }
        return result;
    }

    //thêm ng đọc
    public void addManagement(String bookID, String bookName, String studentID, String studentName) {
        DataStore.managements.add(new Management(bookID, bookName, studentID, studentName));
    }

    //xóa người đọc
    public void removeManagement(String bookID, String studentID) {
        DataStore.managements.removeIf(management ->management.getManagerIDBook().equals(bookID) && management.getManagerIDStudent().equals(studentID));
    }

    //trả sách
    public void returnBook(String bookId, String studentId) {
        for (Management management : DataStore.managements) {
            if (management.getManagerIDBook().equals(bookId) && management.getManagerIDStudent().equals(studentId)) {
                DataStore.managements.remove(management);
                return;
            }
        }
        throw new RuntimeException("Book with ID " + bookId + " not found for student with ID " + studentId);
    }
}

