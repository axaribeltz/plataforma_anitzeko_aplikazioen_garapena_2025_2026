package azterketa.Modeloa.db;

import azterketa.Modeloa.Bezeroak;
import azterketa.Modeloa.Produktuak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBBiltegia {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/biltegia";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public DBBiltegia() {
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

    public List<Bezeroak> bezeroakLortu() {
        String sql = "SELECT * FROM bezeroak";
        List<Bezeroak> bezeroak = new ArrayList<>();

        try (Connection konexioa = konektatu();
             Statement stmt = konexioa.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String izena = rs.getString("izena");
                String helbidea = rs.getString("helbidea");
                String postaKodea = rs.getString("postaKodea");
                String telefonoa = rs.getString("telefonoa");


                bezeroak.add(new Bezeroak(id, izena, helbidea, postaKodea, telefonoa));
            }

        } catch (SQLException e) {
            System.err.println("Bezeroa lortzeko errorea: " + e.getMessage());
        }
        return bezeroak;
    }

    public void bezeroaGehitu(String id, String izena, String helbidea, String postaKodea, String telefonoa) {
        String sql = "INSERT INTO erabiltzaile_mugimenduak (id, izena, helbidea, postaKodea, telefonoa) VALUES (?, ?, ?, ?, ?)";

        try (Connection konexioa = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = konexioa.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(1, izena);
            pstmt.setString(1, helbidea);
            pstmt.setString(1, postaKodea);
            pstmt.setString(1, telefonoa);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Bezeroa gehitzeko errorea: " + e.getMessage());
        }
    }

    public void bilatuProduktuakIzakinarenArabera(int izakinak) {
        String sql = "SELECT * FROM produktuak WHERE izakinak = ?";
        List<Produktuak> produktuak = new ArrayList<>();

        try (Connection konexioa = konektatu();
             Statement stmt = konexioa.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                String hornitzailea = rs.getString("helbidea");
                double prezioa = rs.getDouble("prezioa");

                produktuak.add(new Produktuak(id, izena, hornitzailea, prezioa, izakinak));
            }
        } catch (SQLException e) {
            System.err.println("Produktuak bilatzeko errorea: " + e.getMessage());
        }
    }
}
