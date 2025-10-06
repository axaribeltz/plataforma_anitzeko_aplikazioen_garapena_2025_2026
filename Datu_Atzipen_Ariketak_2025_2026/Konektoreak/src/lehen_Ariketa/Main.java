package lehen_Ariketa;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        getKonexioa();
        imprimatuIzenak();
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
}