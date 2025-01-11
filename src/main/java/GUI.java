import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/*
 Klasa GUI jest implementuje interfejs graficzny dla całego systemu, wykorzystującą biblioteki Swing w języku Java potrzebne do JFrame.
 Działa jako UI do wykonywania operacji na studentach za pomocą obiektu klasy StudentManager.
 Pola tekstowe - "private final JTextField"
 Przyciski operacyjne - "private final JButton"
 */

public class GUI extends JFrame implements ActionListener
{
    private final JTextField nameField, ageField, gradeField, studentIDField;
    private final JButton addStudent, removeStudent, updateStudent, displayAllStudents, calculateAverage;
    private final StudentManager manager;

    public GUI(StudentManager manager)
    {
        this.manager = manager;

        JFrame frame = new JFrame("Advanced Student Managing System");
        JPanel panel = new JPanel();

        /*
        Dodawanie Swingów (pól tekstowych, etykiet i przycisków) do wyświetlanego panelu
        Nasłuchiwanie zdarzeń do każdego przycisku, przypisując bieżącą instancję klasy (this) jako nasłuchiwacza.
        Dodanie paru usprawnień do wyświetlanego panelu.
         */

        nameField = new JTextField(10);
        ageField = new JTextField(10);
        gradeField = new JTextField(10);
        studentIDField = new JTextField(10);

        addStudent = new JButton("Add Student");
        removeStudent = new JButton("Remove Student");
        updateStudent = new JButton("Update Student");
        displayAllStudents = new JButton("Display All Students");
        calculateAverage = new JButton("Calculate Average");

        addStudent.addActionListener(this);
        removeStudent.addActionListener(this);
        updateStudent.addActionListener(this);
        displayAllStudents.addActionListener(this);
        calculateAverage.addActionListener(this);

        panel.add(new JLabel("Name: "));
        panel.add(nameField);
        panel.add(new JLabel("Age: "));
        panel.add(ageField);
        panel.add(new JLabel("Grade: "));
        panel.add(gradeField);
        panel.add(new JLabel("Student ID: "));
        panel.add(studentIDField);
        panel.add(addStudent);
        panel.add(removeStudent);
        panel.add(updateStudent);
        panel.add(displayAllStudents);
        panel.add(calculateAverage);

        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamykanie GUI na X
    }

    /*
    Metoda actionPerformed(ActionEvent e):
    e.get.Soucrce() gra tutaj kluczową rolę, ponieważ wykonuje operacje zależne od źródła.
    Przykładowo dla addStudent pobiera wprowadzone dane z pól tekstowych i tworzy nowego Studenta, wywołuje metodę manager.addStudent(student),
    dla removeStudent wywołuje metodę manager.removeStudent(studentID) z ID wprowadzonego studenta. itd.
     */


    @Override
    public void actionPerformed(ActionEvent e)
    {
        try {
            if (e.getSource() == addStudent)
            {
                Student student = new Student(studentIDField.getText(),
                                              nameField.getText(),
                                              Integer.parseInt(ageField.getText()),
                                              Double.parseDouble(gradeField.getText()));
                manager.addStudent(student);

            }
            else if (e.getSource() == removeStudent)
            {
                manager.removeStudent(studentIDField.getText());

            }
            else if (e.getSource() == updateStudent)
            {
                Student student = new Student(studentIDField.getText(),
                                              nameField.getText(),
                                              Integer.parseInt(ageField.getText()),
                                              Double.parseDouble(gradeField.getText()));
                manager.updateStudent(student);
            }
            else if (e.getSource() == displayAllStudents)
            {
                StringBuilder result = new StringBuilder("Student List:\n");
                for (Student student : manager.getAllStudents())
                {
                    result.append("StudentID: ").append(student.getStudentID())
                            .append(", Name: ").append(student.getName())
                            .append(", Age: ").append(student.getAge())
                            .append(", Grade: ").append(student.getGrade())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(this, result.toString());
            }
            else if (e.getSource() == calculateAverage)
            {
                JOptionPane.showMessageDialog(this, "Average grade: " + manager.calculateAverageGrade());
            }
        }
        //Obsługa wyjątków i błedów oraz wyświetlanie ich w wyskakującym panelu.
        catch (SQLException | IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
