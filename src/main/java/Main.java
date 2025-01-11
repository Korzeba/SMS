import org.example.models.ConnectDB;

import javax.swing.*;
import java.sql.Connection;

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
            System.out.println("Nie udało się nawiązać połączenia z bazą danych.");
        }
    }
}
