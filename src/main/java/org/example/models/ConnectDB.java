package org.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
Klasa wykorzystuje sterownik JDBC do SQLite, łącząc sie z plikiem database.db
Metoda getConnection() służy do uzyskania aktywnego połączenia.
Metoda closeConnection() zapewnia poprawne zamknięcie połączenia, gdy nie jest już potrzebne.
A jeśli tabela students nie istnieje, jest tworzona przy użyciu polecenia SQL w metodzie initializeDatabase().
ConnectDB() - Tworzy obiekt połączebua z DB i łączy się z plikiem SQLite określonym w zmiennej url.
getConnection() - Zwraca obiekt połączenia Connection i pozwala innym klasom,
np. implementacji StudentManagerImpl, korzystać z tego samego połączenia z bazą danych.
 */


public class ConnectDB {
    private Connection connection;

    public ConnectDB() {
        String url = "jdbc:sqlite:database.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            System.out.println("Error Connecting to Database");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection Closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeDatabase() {
        String createTableSQL="CREATE TABLE IF NOT EXISTS students (name TEXT, age INTEGER, grade REAL, studentID TEXT PRIMARY KEY)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table Initialized");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
