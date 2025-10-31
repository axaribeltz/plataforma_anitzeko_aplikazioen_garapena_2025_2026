package hirugarren_ariketa.Models.db;

import hirugarren_ariketa.Models.ErabiltzaileMugimenduak;

import java.sql.*;
import java.util.ArrayList;

public class Connect {
    public Connection conexion() {
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

    public ArrayList<ErabiltzaileMugimenduak> getErabiltzaileMugimenduak() throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "SELECT * FROM erabiltzaile_mugimenduak";
        ResultSet rs = st.executeQuery(consulta);
        ArrayList<ErabiltzaileMugimenduak> lista = new ArrayList<>();
        while (rs.next()) {
            ErabiltzaileMugimenduak t = new ErabiltzaileMugimenduak(
                    rs.getString("izena"),
                    rs.getInt("kantitatea")
            );
            lista.add(t);
        }
        con.close();
        return lista;
    }

    public void transakzioaEgin(String izena1, double kantitatea, String izena2) throws SQLException {
        Connection con = null;

        try {
            con = conexion();
            con.setAutoCommit(false);

            // Lehenengo pertsonari kantitatea kendu edo eguneratu
            eguneratuEdoTxertatu(con, izena1, -kantitatea);

            // Bigarren pertsonari kantitatea gehitu edo eguneratu
            eguneratuEdoTxertatu(con, izena2, kantitatea);

            con.commit();
            System.out.println("Transakzioa ondo burutu da.");

        } catch (SQLException e) {
            System.err.println("Errorea transakzioan: " + e.getMessage());
            try {
                con.rollback();
                System.out.println("Transakzioa atzera bota da.");
            } catch (SQLException ex) {
                System.err.println("Errorea rollback egiterakoan: " + ex.getMessage());
            }
            throw e; // Salbuespena berriro bota
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Errorea konexioa ixterakoan: " + e.getMessage());
                }
            }
        }
    }

    private void eguneratuEdoTxertatu(Connection con, String izena, double kantitatea) throws SQLException {
        // Lehenik, egiaztatu izena existitzen den
        String selectSQL = "SELECT kantitatea FROM erabiltzaile_mugimenduak WHERE izena = ?";
        try (PreparedStatement selectStmt = con.prepareStatement(selectSQL)) {
            selectStmt.setString(1, izena);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // Izena existitzen da, beraz, kantitatea eguneratu
                double kantitateaZaharra = rs.getDouble("kantitatea");
                String updateSQL = "UPDATE erabiltzaile_mugimenduak SET kantitatea = ? WHERE izena = ?";
                try (PreparedStatement updateStmt = con.prepareStatement(updateSQL)) {
                    updateStmt.setDouble(1, kantitateaZaharra + kantitatea);
                    updateStmt.setString(2, izena);
                    updateStmt.executeUpdate();
                    System.out.println(izena + " erabiltzailearen kantitatea eguneratu da.");
                }
            } else {
                // Izena ez da existitzen, beraz, txertatu berria
                String insertSQL = "INSERT INTO erabiltzaile_mugimenduak (izena, kantitatea) VALUES (?, ?)";
                try (PreparedStatement insertStmt = con.prepareStatement(insertSQL)) {
                    insertStmt.setString(1, izena);
                    insertStmt.setDouble(2, kantitatea);
                    insertStmt.executeUpdate();
                    System.out.println(izena + " erabiltzailea txertatu da.");
                }
            }
        }
    }
}