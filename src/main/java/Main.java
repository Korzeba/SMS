import org.example.models.ConnectDB; // Implementacjia klasy ConnectDB - łączenie z baza danych
import javax.swing.*; // Implementacjia bibloteki JFrame - tworzenie GUI
import java.sql.Connection; // Implementacja klasy potrzebnej do połączenia z DB
import java.sql.SQLException; // Implementacja klasy potrzebnej do wyjątków SQL
import java.sql.Statement;   // Implementacja klas potrzebnych do zapytan SQL

public class Main
{
    public static void main(String[] args)
    {
        GUI window = new GUI(); // Tworzenie nowego GUI
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamykanie GUI na X
    }
}