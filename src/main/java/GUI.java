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

    public GUI() {
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
}
