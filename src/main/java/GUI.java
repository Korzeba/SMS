import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GUI extends JFrame implements ActionListener {
    private final JTextField nameField, ageField, gradeField, studentIDField;
    private final JButton addStudent, removeStudent, updateStudent, displayAllStudents, calculateAverage;
    private final StudentManager manager;

    public GUI(StudentManager manager) {
        this.manager = manager;

        JFrame frame = new JFrame("Zaawansowany system zarządzania studentami");
        JPanel panel = new JPanel();

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

        panel.add(new JLabel("Imie: "));
        panel.add(nameField);
        panel.add(new JLabel("Wiek: "));
        panel.add(ageField);
        panel.add(new JLabel("Ocena: "));
        panel.add(gradeField);
        panel.add(new JLabel("ID: "));
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

    @Override
    public void actionPerformed(ActionEvent e) {
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
                StringBuilder result = new StringBuilder("Lista Studentów:\n");
                for (Student student : manager.getAllStudents())
                {
                    result.append("ID: ").append(student.getStudentID())
                            .append(", Imię: ").append(student.getName())
                            .append(", Wiek: ").append(student.getAge())
                            .append(", Ocena: ").append(student.getGrade())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(this, result.toString());
            }
            else if (e.getSource() == calculateAverage)
            {
                JOptionPane.showMessageDialog(this, "Średnia ocena: " + manager.calculateAverageGrade());
            }
        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Błąd: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
