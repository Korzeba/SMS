package org.example.models; // Implementacjia klasy ConnectDB - łączenie z DB
import java.sql.Connection; // Implementacja klasy potrzebnej do połączenia z DB
import java.sql.DriverManager; // Import klasy DriverManager do zarządzania połączeniami z DB
import java.sql.SQLException; // Import klasy SQLException do obsługi wyjątków związanych z SQL.
import java.sql.Statement;

public class ConnectDB // Klasa łączenia z DB
{
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
    public Connection connection; //przechowywanie obecnego polaczenia z DB
    public ConnectDB()
    {
        String url = "jdbc:sqlite:database.db"; // Ścieżka do pliku DB SQLite.
        try
        { // Próba nawiązania połączenia z DB.
            connection = DriverManager.getConnection(url);
            System.out.println("Connection Successful");
        }
        catch (SQLException e) // Obsługa wyjątków związanych z SQL.
        {
            System.out.println("Error Connecting to Database");
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return connection;
    } // Metoda do uzyskania aktywnego połączenia z DB i zwarca connection
    public void closeConnection() // Metoda do zamknięcia połączenia z bazą danych.
    {
        try {

            if (connection != null) { // Sprawdzenie, czy istnieje aktywne połączenie.
                System.out.println("Connection Closed");
                connection.close(); // zamkniecie połącznia
            }
        }
        catch (SQLException e) // Obsługa wyjątków podczas zamykania połączenia.
        {
            e.printStackTrace();
        }
    }
}