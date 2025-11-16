/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2AM3-4
 */
public class DBBezeroak {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cliente";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public DBBezeroak() {
        konektatu();
    }

    public Connection konektatu() {
        Connection connection = null;
        try {
            // Cargar driver opcionalmente
            //Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            if (connection != null) {
                System.out.println("Konexioa ondo eginda");
            }
        } catch (SQLException e) {
            System.out.println("Errorea konektatzen: " + e.getMessage());
        }
        return connection;
    }

    public List<Bezeroak> bezeroakLortu() {
        String sql = "SELECT * FROM cliente";
        List<Bezeroak> bezeroak = new ArrayList<>();

        try (Connection konexioa = konektatu(); Statement stmt = konexioa.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("nombres");
                String hiria = rs.getString("ciudad");
                String sexua = rs.getString("sexo");
                String mugikorra = rs.getString("telefono");
                LocalDate jaiotzeData = rs.getDate("fecha_nacimiento").toLocalDate();

                bezeroak.add(new Bezeroak(id, izena, hiria, sexua, mugikorra, jaiotzeData));
            }

        } catch (SQLException e) {
            System.err.println("Bezeroa lortzeko errorea: " + e.getMessage());
        }
        return bezeroak;
    }

    public void bezeroaEzabatu(int bezeroId) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection konexioa = konektatu(); PreparedStatement pstmt = konexioa.prepareStatement(sql)) {

            pstmt.setInt(1, bezeroId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Bezeroa ezabatuta, ID: " + bezeroId);
            } else {
                System.out.println("Ez da bezerorik aurkitu ID honekin: " + bezeroId);
                throw new SQLException("Ez da bezerorik aurkitu ID honekin: " + bezeroId);
            }

        } catch (SQLException e) {
            System.err.println("Errorea bezeroa ezabatzean: " + e.getMessage());
            throw e; // Relanzar la excepción para que se maneje en la capa superior
        }
    }

    public void bezeroaGehitu(Bezeroak bezeroa) throws SQLException {
        String sql = "INSERT INTO cliente (nombres, ciudad, sexo, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)";

        try (Connection konexioa = konektatu(); PreparedStatement pstmt = konexioa.prepareStatement(sql)) {

            pstmt.setString(1, bezeroa.getIzena());
            pstmt.setString(2, bezeroa.getHiria());
            pstmt.setString(3, bezeroa.getSexua());
            pstmt.setString(4, bezeroa.getMugikorra());
            pstmt.setDate(5, bezeroa.getJaiotzeData() != null ? Date.valueOf(bezeroa.getJaiotzeData()) : null);  // Convertir LocalDate a Date

            pstmt.executeUpdate();
            System.out.println("Bezeroa gehitu da: " + bezeroa.getIzena());

        } catch (SQLException e) {
            System.err.println("Errorea bezeroa gehitzean: " + e.getMessage());
            throw e;
        }
    }

    public void bezeroaEguneratu(Bezeroak bezeroa) throws SQLException {
        String sql = "UPDATE cliente SET nombres=?, ciudad=?, sexo=?, telefono=?, fecha_nacimiento=? WHERE id=?";

        try (Connection konexioa = konektatu(); PreparedStatement pstmt = konexioa.prepareStatement(sql)) {

            pstmt.setString(1, bezeroa.getIzena());
            pstmt.setString(2, bezeroa.getHiria());
            pstmt.setString(3, bezeroa.getSexua());
            pstmt.setString(4, bezeroa.getMugikorra());
            pstmt.setDate(5, Date.valueOf(bezeroa.getJaiotzeData()));
            pstmt.setInt(6, bezeroa.getId());

            pstmt.executeUpdate();
            System.out.println("Bezeroa eguneratua: " + bezeroa.getId());

        } catch (SQLException e) {
            System.err.println("Errorea eguneratzean: " + e.getMessage());
            throw e;
        }
    }
}
