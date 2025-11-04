package SQLLite.Modeloa.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SQLLite.Modeloa.Zeregina;


public class DBZeregina {
    // Datu-basearen URL-a
    private static final String DB_URL = "jdbc:mysql://localost:3306/";
    
    public DBZeregina() {
		konektatu();
	}
    private void konektatu() {
    	try {
            Class.forName("org.sqlite.JDBC"); 
            taulaSortu();
        } catch (ClassNotFoundException e) {
            System.err.println("Errorea: SQLite driver-a ez da aurkitu.");
            e.printStackTrace();
        }

	}

    private void taulaSortu() {
        String sql = "CREATE TABLE IF NOT EXISTS zereginak (\n"
                + " id integer PRIMARY KEY,\n"
                + " deskribapena text NOT NULL,\n"
                + " burutua boolean NOT NULL DEFAULT 0\n"
                + ");";

        try (Connection konexioa = DriverManager.getConnection(DB_URL);
             Statement stmt = konexioa.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Taula sortzeko errorea: " + e.getMessage());
        }
    }

    public void zereginaGehitu(String deskribapena) {
        String sql = "INSERT INTO zereginak(deskribapena) VALUES(?)";

        try (Connection konexioa = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = konexioa.prepareStatement(sql)) {
            pstmt.setString(1, deskribapena);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Zeregina gehitzeko errorea: " + e.getMessage());
        }
    }

    public List<Zeregina> zereginGuztiakLortu() {
        String sql = "SELECT id, deskribapena, burutua FROM zereginak ORDER BY burutua, id";
        List<Zeregina> zereginak = new ArrayList<>();

        try (Connection konexioa = DriverManager.getConnection(DB_URL);
             Statement stmt = konexioa.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String desk = rs.getString("deskribapena");
                boolean burutua = rs.getInt("burutua") == 1; // 1 = true
                zereginak.add(new Zeregina(id, desk, burutua));
            }
        } catch (SQLException e) {
            System.err.println("Zereginak lortzeko errorea: " + e.getMessage());
        }
        return zereginak;
    }

    public void burututzatMarkatu(int zereginId) {
        String sql = "UPDATE zereginak SET burutua = 1 WHERE id = ?";

        try (Connection konexioa = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = konexioa.prepareStatement(sql)) {
            pstmt.setInt(1, zereginId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Zeregina markatzeko errorea: " + e.getMessage());
        }
    }
}