import javax.swing.*;
import java.sql.*;

public class StudentManager
{
    private void addStudent(Connection connection) throws SQLException { // Pobranie danych z pól tekstowych i ich walidacja.
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String gradeText = gradeField.getText().trim();
        String studentID = studentIDField.getText().trim();

        // Sprawdzenie wieku
        int age; // Deklaracja zmiennej age
        try {
            age = Integer.parseInt(ageText); // Konwersja String do int
            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Wiek musi być liczbą dodatnią.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Wiek musi być liczbą całkowitą dodatnią.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Sprawdzenie oceny
        double grade; // Deklaracja zmiennej grade
        try {
            grade = Double.parseDouble(gradeText); // Konwersja String do double
            if (grade < 0.0 || grade > 100.0) { // warunek - liczba zmiennoprzecinkowa w przedziale 0-100
                JOptionPane.showMessageDialog(this, "Ocena musi być w przedziale od 0 do 100.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ocena musi być liczbą zmiennoprzecinkową w przedziale od 0 do 100.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Dodanie studenta do bazy
        String sql = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentID);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setDouble(4, grade);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student dodany pomyślnie.");
        }
    }


    private void removeStudent(Connection connection) throws SQLException // Usuwanie studenta
    {
        String studentID = studentIDField.getText();

        if (studentID.trim().isEmpty()) //sprawdzenie czy ID jest puste, jesli tak to error.
        {
            JOptionPane.showMessageDialog(this, "ID studenta nie może być puste.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "DELETE FROM students WHERE studentID = ?"; //komenda SQL

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, studentID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) { // Usuwanie Studenta z listy, jesli taki jest
                JOptionPane.showMessageDialog(this, "Student pomyślnie usunięty.");
            } else {
                JOptionPane.showMessageDialog(this, "Nie znaleziono studenta.");
            }
        }
    }

    private void updateStudent(Connection connection) throws SQLException // Nadpisywanie
    {
        String studentID = studentIDField.getText(); //Pobieranie danych z pól tekstowych i ich walidacja
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        double grade = Double.parseDouble(gradeField.getText());

        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?"; // SQL, nadpisanie wczesniej zapisanych pól
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setDouble(3, grade);
            stmt.setString(4, studentID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0)   //warunek czy zmiany zostały popełnione, jesli ID studenta sie zgadza
            {
                JOptionPane.showMessageDialog(this, "Status studenta zaktualizowany pomyślnie.");
            } else
            {
                JOptionPane.showMessageDialog(this, "Nie znaleziono studenta.");
            }
        }
    }

    private void displayAllStudents(Connection connection) throws SQLException // Wyświetlenie listy studentow
    {
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            StringBuilder result = new StringBuilder("Lista Studentów:\n"); // Wypisanie kolejnych elementow tabeli
            while (rs.next()) {
                result.append("ID Studenta: ").append(rs.getString("studentID"))
                        .append(", Imie Studenta: ").append(rs.getString("name"))
                        .append(", Wiek Studenta: ").append(rs.getInt("age"))
                        .append(", Ocena Studenta: ").append(rs.getDouble("grade"))
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, result.toString()); // Wypisanie danych na panelu
        }
    }

    private void calculateAverageGrade(Connection connection) throws SQLException
    {
        String sql = "SELECT AVG(grade) AS averageGrade FROM students"; //SQl licznie sredniej
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            if (rs.next())
            {
                double averageGrade = rs.getDouble("averageGrade"); // Wypisanie sredniej
                JOptionPane.showMessageDialog(this, "Średnia ocena: " + averageGrade);
            }
        }
    }

}
