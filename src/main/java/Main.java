import org.example.models.ConnectDB;

import javax.swing.*;
import java.sql.Connection;

/*
Obiekt klasy ConnectDB (db) odpowiada za nawiązanie połączenia z bazą danych a Metoda initializeDatabase() sprawdza,
czy tabela students istnieje, i tworzy ją w razie potrzeby.Po nawiązaniu połączenia za pomocą db.getConnection(),
obiekt połączenia jest przekazywany do innych komponentów aplikacji.
Tworzony jest obiekt StudentManagerImpl, który implementuje interfejs StudentManager a
obiekt ten korzysta z nawiązanego wcześniej połączenia z bazą danych. Finalnie tworzony jest inasz interfejs urzytkownika.
Główna metoda main() zawiera blok try-catch,
który przechwytuje wszelkie wyjątki występujące podczas inicjalizacji aplikacji i wyświetla je na konsoli.
 */

public class Main {
    public static void main(String[] args) {
        ConnectDB db = new ConnectDB();
        db.initializeDatabase();

        Connection connection = db.getConnection();  // Pobierz połączenie, ale nie zamykaj go
        if (connection != null) {
            try {
                StudentManager manager = new StudentManagerImpl(connection);
                GUI window = new GUI(manager);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error Connecting to Database");
        }
    }
}
