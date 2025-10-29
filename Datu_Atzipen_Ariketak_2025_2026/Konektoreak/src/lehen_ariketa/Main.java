package lehen_ariketa;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        getKonexioa();
        imprimatuIzenak();
        //erakutsiKantitatea();
        gehituEdoEgiaztatuErabiltzailea();
    }

    private static Connection getKonexioa() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/kutxabank";
        String username = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Datu basera konektatzen");
        } catch (SQLException e) {
            System.err.println("Konexio errorea egon da datu basearekin");
            e.printStackTrace();
        }
        return con;
    }

    private static void imprimatuIzenak() {
        Connection con = getKonexioa();
        ArrayList<String> bezeroenZerrenda = new ArrayList<>();

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT izena FROM erabiltzaile_mugimenduak");
            while (rs.next()) { //Lerroz lerro doa next-ekin
                bezeroenZerrenda.add(rs.getString("izena"));
            }
        } catch (SQLException e) {
            System.err.println("Izenak imprimatzerakoan errorea eman du");
            e.printStackTrace();
        }
        System.out.println(bezeroenZerrenda);
    }

    private static void erakutsiKantitatea() {
        try (Scanner sc = new Scanner(System.in);
             Connection con = getKonexioa()) {

            System.out.print("Sartu erabiltzailearen izena: ");
            String izena = sc.nextLine();

            String sql = "SELECT kantitatea FROM erabiltzaile_mugimenduak WHERE izena = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, izena);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int kantitatea = rs.getInt("kantitatea");
                    System.out.println(izena + " erabiltzailearen kantitatea da: " + kantitatea);
                } else {
                    System.out.println("Ez da aurkitu erabiltzailea: " + izena);
                }
            }

        } catch (SQLException e) {
            System.err.println("Errorea datu basean kontsulta egitean.");
            e.printStackTrace();
        }
    }

    private static void gehituEdoEgiaztatuErabiltzailea() {
        try (Scanner sc = new Scanner(System.in);
             Connection con = getKonexioa()) {

            System.out.print("Sartu erabiltzailearen izena: ");
            String izena = sc.nextLine();

            System.out.print("Sartu kantitatea: ");
            int kantitatea = sc.nextInt();

            // Lehenik begiratu erabiltzailea existitzen den
            String sqlSelect = "SELECT kantitatea FROM erabiltzaile_mugimenduak WHERE izena = ?";
            try (PreparedStatement pst = con.prepareStatement(sqlSelect)) {
                pst.setString(1, izena);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    // Existitzen da → erakutsi bere kantitatea
                    int dbKantitatea = rs.getInt("kantitatea");
                    System.out.println("Erabiltzailea '" + izena + "' existitzen da. Kantitatea: " + dbKantitatea);
                } else {
                    // Ez bada existitzen → erregistro berria sartu
                    String sqlInsert = "INSERT INTO erabiltzaile_mugimenduak (izena, kantitatea) VALUES (?, ?)";
                    try (PreparedStatement pstInsert = con.prepareStatement(sqlInsert)) {
                        pstInsert.setString(1, izena);
                        pstInsert.setInt(2, kantitatea);
                        int rows = pstInsert.executeUpdate();

                        if (rows > 0) {
                            System.out.println("Erabiltzaile berria gehitu da: " + izena + " (kantitatea: " + kantitatea + ")");
                        } else {
                            System.out.println("Errorea erabiltzailea gehitzean.");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Errorea datu basean.");
            e.printStackTrace();
        }
    }
}
