package hirugarren_ariketa.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private Connection conexion() {
        String url = "jdbc:mysql://localhost:3306/kutxabank";
        String username = "root";
        String password = "";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Errorea konektatzen: " + e.getMessage());
        }
        return connection;
    }
}