import org.example.models.ConnectDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentManagerImp
{
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
}
