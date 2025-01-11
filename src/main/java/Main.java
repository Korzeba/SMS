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
        ConnectDB db = new ConnectDB(); // Połączenie z DB
        Connection connection = db.getConnection(); // Pobranie connection - wizualizcja aktywnego połączenia z DB
        if (connection != null) // Sprawdzenie połączenia z DB
        {
            try // Blok try-catch do obsługi potencjalnych błędów podczas operacji na bazie danych.
            {
                Statement stmt= connection.createStatement(); // Stworzenie stmt - korzystanie z SQL
                String createTable="CREATE TABLE IF NOT EXISTS students (name TEXT, age INTEGER, grade REAL, studentID TEXT PRIMARY KEY)"; // SQL name - tekst, age - liczba calkowita, grade - liczba zmiennoprzecinkowa, studentID - primarykey tekst
                stmt.executeUpdate(createTable); // Tworzenie tabeli jeśli jeszcze jej ni stworzono
            }
            catch (SQLException e) // Blok catch obsługujący wyjątki związane z SQL.
            {
                e.printStackTrace(); // Wypisanie błedu do konsoli
            }
            finally // zakonczenie bloku
            {
                db.closeConnection(); // zamkniecie połączenia z DB
            }
        }
        else // wykonanie jesli zamkniecie połaczenia sie nie udało
        {
            System.out.println("Connection failed.");
        }

    }
}