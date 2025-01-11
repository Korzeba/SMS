import java.sql.SQLException;
import java.util.List;

/*
Interface definiujący dodawanie, usuwanie, aktualizowanie, pobieranie danych studentów i obliczanie średniej ocen.
Dla wyjątków używany jest SQLException rzucany w przypadku problemów z bazą danych.
 */

public interface StudentManager
{
    void addStudent(Student student) throws SQLException;
    void removeStudent(String studentID) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
    double calculateAverageGrade() throws SQLException;
}
