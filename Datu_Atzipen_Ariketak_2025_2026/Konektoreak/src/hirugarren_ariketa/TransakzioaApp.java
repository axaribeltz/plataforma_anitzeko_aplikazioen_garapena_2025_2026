package hirugarren_ariketa;

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
        } while (aukera != 3);
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

    private static void transakzioa(Connect con) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sartu lehenengo pertsonaren izena (kenketa egingo zaiona):");
        String izena1 = sc.nextLine();

        System.out.println("Sartu bigarren pertsonaren izena (gehiketa egingo zaiona):");
        String izena2 = sc.nextLine();

        System.out.println("Sartu kantitatea:");
        double kantitatea = Double.parseDouble(sc.nextLine());

        try {
            con.transakzioaEgin(izena1, kantitatea, izena2);
            System.out.println("Transakzioa ondo burutu da.");

        } catch (SQLException e) {
            System.err.println("Errorea transakzioan: " + e.getMessage());
            System.out.println("Transakzioa ez da burutu."); // Mezua aldatu
        }
    }
}