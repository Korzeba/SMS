import org.example.models.ConnectDB; // Implementacjia klasy ConnectDB - łączenie z baza danych
import javax.swing.*; // Implementacjia bibloteki JFrame - tworzenie GUI
import java.awt.event.ActionEvent; // Implementacjia klasy ActionEvent do obsługi zdarzeń.
import java.awt.event.ActionListener; // Implementacjia klasy ActionListener do implementacji obsługi zdarzeń.
import java.sql.*; // Import klas do obsługi SQL i połączeń z DB.

public class GUI extends JFrame implements ActionListener // Klasa dziedzicząca JFrame i implementacja ActionListener (używanie this przez cały kod)
{
    private final JLabel nameLabel, ageLabel, gradeLabel, studentIDLabel; //Labele - text
    private final JTextField nameField, ageField, gradeField, studentIDField; //Pola textowe
    private final JButton addStudent, removeStudent, updateStudent, displayAllStudents, calculateAverage; // Buttons

    public GUI()
    {
        JFrame frame = new JFrame("Zaawansowany system zarządzania studentami"); // Tytuł i stworzenie okna GUI
        JPanel panel = new JPanel(); // Stworzenie panelu dla powyższych komponentów

        // Inicjalizacja labelów tekstowych
        nameLabel = new JLabel("Imie Studenta: ");
        ageLabel = new JLabel("Wiek Studenta: ");
        gradeLabel = new JLabel("Ocena Studenta: ");
        studentIDLabel = new JLabel("ID Studenta : ");
        // Inicjalizacja pól tekstowych
        nameField = new JTextField(10);
        ageField = new JTextField(10);
        gradeField = new JTextField(10);
        studentIDField = new JTextField(10);
        // Inicjalizacja przycisków i ich nazw
        addStudent = new JButton("Add Student");
        removeStudent = new JButton("Remove Student");
        updateStudent = new JButton("Update Student");
        displayAllStudents = new JButton("Display All Students");
        calculateAverage = new JButton("Calculate Average");
        // Rejestracja przyciskow
        addStudent.addActionListener(this);
        removeStudent.addActionListener(this);
        updateStudent.addActionListener(this);
        displayAllStudents.addActionListener(this);
        calculateAverage.addActionListener(this);
        // Dodanie komponentów do panelu
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(studentIDLabel);
        panel.add(studentIDField);
        panel.add(addStudent);
        panel.add(removeStudent);
        panel.add(updateStudent);
        panel.add(displayAllStudents);
        panel.add(calculateAverage);
        // Dodanie panelu do ramki
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamykanie GUI na X
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) // Obsługa zdarzeń przycisków.
    {
        ConnectDB db = new ConnectDB(); // Połączenie z DB
        try (Connection connection = db.getConnection()) // Pobranie connection - wizualizcja aktywnego połączenia z DB
        { // Przyciski i ich obsługa
            if (e.getSource() == addStudent)
            {
                addStudent(connection);
            } else if (e.getSource() == removeStudent)
            {
                removeStudent(connection);
            } else if (e.getSource() == updateStudent)
            {
                updateStudent(connection);
            } else if (e.getSource() == displayAllStudents)
            {
                displayAllStudents(connection);
            } else if (e.getSource() == calculateAverage)
            {
                calculateAverageGrade(connection);
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage()); //
        }
    }

    private void addStudent(Connection connection) throws SQLException { // Pobranie danych z pól tekstowych i ich walidacja.
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String gradeText = gradeField.getText().trim();
        String studentID = studentIDField.getText().trim();

        // Sprawdzenie imienia
        if (!name.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Imię może zawierać tylko litery.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
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