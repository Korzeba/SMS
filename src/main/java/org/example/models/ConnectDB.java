package org.example.models; // Implementacjia klasy ConnectDB - łączenie z DB
import java.sql.Connection; // Implementacja klasy potrzebnej do połączenia z DB
import java.sql.DriverManager; // Import klasy DriverManager do zarządzania połączeniami z DB
import java.sql.SQLException; // Import klasy SQLException do obsługi wyjątków związanych z SQL.
public class ConnectDB // Klasa łączenia z DB
{
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