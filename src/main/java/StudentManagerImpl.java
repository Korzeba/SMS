import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagerImpl extends Component implements StudentManager
{
    private final Connection connection;

    public StudentManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentID());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.setDouble(4, student.getGrade());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student został pomyślnie dodany.", "Sukces", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    @Override
    public void removeStudent(String studentID) throws SQLException {
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student został pomyślnie usunięty.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nie znaleziono studenta o podanym ID.", "Ostrzeżenie", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setDouble(3, student.getGrade());
            stmt.setString(4, student.getStudentID());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Dane studenta zostały pomyślnie zaktualizowane.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nie znaleziono studenta o podanym ID.", "Ostrzeżenie", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double grade = rs.getDouble("grade");
                students.add(new Student(studentID, name, age, grade));
            }
        }
        return students;
    }

    @Override
    public double calculateAverageGrade() throws SQLException {
        String sql = "SELECT AVG(grade) AS averageGrade FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("averageGrade");
            }
        }
        return 0.0;
    }
}
