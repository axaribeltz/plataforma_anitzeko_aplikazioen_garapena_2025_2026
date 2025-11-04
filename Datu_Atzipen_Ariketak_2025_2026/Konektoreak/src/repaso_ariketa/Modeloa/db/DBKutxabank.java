package repaso_ariketa.Modeloa.db;

import repaso_ariketa.Modeloa.ErabiltzaileMugimenduak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBKutxabank {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kutxabank";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public DBKutxabank() {
        konektatu();
    }

    public Connection konektatu() {
        Connection connection = null;
        try {
            // Cargar driver opcionalmente
            //Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Errorea konektatzen: " + e.getMessage());
        }
        return connection;
    }

    public List<ErabiltzaileMugimenduak> erabiltzaileMugimenduakLortu() {
        String sql = "SELECT * FROM erabiltzaile_mugimenduak";
        List<ErabiltzaileMugimenduak> erabiltzaileMugimenduak = new ArrayList<>();

        try (Connection konexioa = konektatu();
             Statement stmt = konexioa.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String izena = rs.getString("izena");
                int kantitatea = rs.getInt("kantitatea");
                erabiltzaileMugimenduak.add(new ErabiltzaileMugimenduak(izena, kantitatea));
            }

        } catch (SQLException e) {
            System.err.println("Zereginak lortzeko errorea: " + e.getMessage());
        }
        return erabiltzaileMugimenduak;
    }
}
