package hirugarren_ariketa;

import bigarren_ariketa.Models.Taldea;
import bigarren_ariketa.Models.db.TaldeaConnect;
import hirugarren_ariketa.Models.ErabiltzaileMugimenduak;
import hirugarren_ariketa.Models.db.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class TransakzioaApp {
    static void main(String[] args) throws SQLException {
        int aukera;
        Connect con = new Connect();
        Scanner sc = new Scanner(System.in);
        do {
            menuaErakutsi();
            aukera = Integer.parseInt(sc.nextLine());
            switch (aukera) {
                case 1 -> erabiltzaileMugimenduGuztiak(con);
                case 2 -> transakzioa(con);
                case 3 -> System.out.println("Amaitu da programa.");
                default -> System.out.println("Aukera okerra.");
            }
        } while(aukera !=3);
    }
    private static void menuaErakutsi() {
        System.out.println("******Transakzio kudeaketa******");
        System.out.println("1- Erabiltzaile mugimendu guztiak ikusi");
        System.out.println("2- Transakzio bat egin");
        System.out.println("3- Irten");
        System.out.println("*********************************");
        System.out.println("Idatzi zure aukera:");
    }

    private static void erabiltzaileMugimenduGuztiak(Connect con) throws SQLException {
        for (ErabiltzaileMugimenduak t : con.getErabiltzaileMugimenduak()) System.out.println(t);
    }

    private static void transakzioa(Connect con) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sartu lehenengo pertsonaren izena (kenketa egingo zaiona):");
        String izena1 = sc.nextLine();

        System.out.println("Sartu bigarren pertsonaren izena (gehiketa egingo zaiona):");
        String izena2 = sc.nextLine();

        System.out.println("Sartu kantitatea:");
        double kantitatea = Double.parseDouble(sc.nextLine());

        Connection conn = null; // Konexioa hemen deklaratu

        try {
            conn = con.getConnection(); // Konexioa lortu
            conn.setAutoCommit(false); // Transakzioa hasi

            // 1. pertsonari kantitatea kendu
            String sql1 = "INSERT INTO ErabiltzaileMugimenduak (izena, kantitatea) VALUES (?, ?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, izena1);
            pstmt1.setDouble(2, -kantitatea);
            pstmt1.executeUpdate();
            pstmt1.close();

            // 2. pertsonari kantitatea gehitu
            String sql2 = "INSERT INTO ErabiltzaileMugimenduak (izena, kantitatea) VALUES (?, ?)";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, izena2);
            pstmt2.setDouble(2, kantitatea);
            pstmt2.executeUpdate();
            pstmt2.close();

            conn.commit(); // Transakzioa konfirmatu
            System.out.println("Transakzioa ondo burutu da.");

        } catch (SQLException e) {
            System.err.println("Errorea transakzioan: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback(); // Transakzioa atzera bota
                    System.out.println("Transakzioa atzera bota da.");
                } catch (SQLException ex) {
                    System.err.println("Errorea rollback egiterakoan: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Auto-commit berrezarri
                    conn.close(); // Konexioa itxi
                } catch (SQLException e) {
                    System.err.println("Errorea konexioa ixterakoan: " + e.getMessage());
                }
            }
        }
    }

}


