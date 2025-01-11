import java.sql.SQLException;
import java.util.List;

public interface StudentManager {
    void addStudent(Student student) throws SQLException;
    void removeStudent(String studentID) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
    double calculateAverageGrade() throws SQLException;
}
