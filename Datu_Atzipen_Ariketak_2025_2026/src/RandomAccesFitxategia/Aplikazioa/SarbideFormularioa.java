package RandomAccesFitxategia.Aplikazioa;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class SarbideFormularioa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aukerak = 3;

        do {
            menuaBistaratu();
        }
        while (condition);

}   }

    private static void menuaBistaratu(){
        System.out.println("\n--- MENUA ---");
        System.out.println("1. Pertsona gorde");
        System.out.println("2. Pertsona irakurri");
        System.out.println("3. Irten");
        System.out.print("Aukeratu aukera bat: ");
    }

    private static void gordePertsona(String izena, int urteak, String herria) {
        String rutaGuztia = "C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\RandomAccesFitxategia\\Aplikazioa\\Pertsona.dat";
        try (RandomAccessFile raf = new RandomAccessFile(rutaGuztia, "rw")) {
            raf.writeUTF("Izena: " + izena);
            raf.writeInt(urteak);
            raf.writeUTF(herria);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void irakurriPertsona() {
        String rutaGuztia = "C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\RandomAccesFitxategia\\Aplikazioa\\Pertsona.dat";
        try (RandomAccessFile raf = new RandomAccessFile(rutaGuztia, "rw")) {
            raf.seek(0);

            String izenaL = raf.readUTF();
            int urteakL = raf.readInt();
            String herriaL = raf.readUTF();

            System.out.println("Izena: " + izenaL);
            System.out.println("Urteak: " + urteakL);
            System.out.println("Herria: " + herriaL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}