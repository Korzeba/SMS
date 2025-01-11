import java.sql.*; // Klasy do obsługi DB (Connection, ResultSet, SQLException itp.)
import java.util.ArrayList; // Klasa ArrayList do przechowywania dynamicznych list danych.
import javax.swing.table.DefaultTableModel; // Klasa DefaultTableModel do budowy modelu tabeli Swing.

public class Tabela { // Klasa Tabela służąca do budowy modelu tabeli z danych uzyskanych z DB
    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException { // Metoda buildTableModel buduje model tabeli na podstawie danych z ResultSet.

        ResultSetMetaData metaData = rs.getMetaData(); // Pobierz metadane z ResultSet (kolumny)
        int columnCount = metaData.getColumnCount(); // Liczba kolumn w wynikach zapytania.

        // Tworzenie listy nazw kolumn na podstawie metadanych.
        ArrayList<String> columnNames = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) { // Iteracja przez wszystkie kolumny (numeracja od 1).
            columnNames.add(metaData.getColumnName(i)); // Dodanie nazwy kolumny do listy.
        }

        // Pobieranie danych wierszy z ResultSet.
        ArrayList<Object[]> rowData = new ArrayList<>(); // Lista do przechowywania wierszy danych.
        while (rs.next()) { // Iteracja przez każdy wiersz wyników.
            Object[] row = new Object[columnCount]; // Tablica do przechowywania danych pojedynczego wiersza.
            for (int i = 1; i <= columnCount; i++) { // Iteracja przez kolumny w wierszu (numeracja od 1).
                row[i - 1] = rs.getObject(i); // Pobieranie wartości z bieżącej kolumny.
            }
            rowData.add(row); // Dodanie wiersza
        }

        // Tworzenie modelu tabeli Swing na podstawie nazw kolumn i danych wierszy.
        DefaultTableModel tableModel = new DefaultTableModel(columnNames.toArray(), 0); // Utworzenie modelu z nagłówkami kolumn.
        for (Object[] row : rowData) { // Iteracja przez wszystkie wiersze.
            tableModel.addRow(row); // Dodanie każdego wiersza do tabeli.
        }

        return tableModel; // Zwracanie tabeli.
    }
}
