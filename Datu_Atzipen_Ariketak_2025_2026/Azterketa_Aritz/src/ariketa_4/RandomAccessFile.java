package ariketa_4;

import java.io.IOException;

public class RandomAccessFile {
    public static void main(String[] args) {
        irakurriTestua();
    }

    public static void irakurriTestua() {
        try (java.io.RandomAccessFile raf = new java.io.RandomAccessFile("\"C:\\\\Users\\\\2AM3-4\\\\Documents\\\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\\\Datu_Atzipen_Ariketak_2025_2026\\\\Azterketa_Aritz\\\\src\\\\ariketa_4\\\\partehartzaileak.dat", "r")) {
            raf.seek(7);
            System.out.println("------ Fitxategiko testua ------");
            String linea;
            while ((linea = raf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("❌ Errorea irakurtzean: " + e.getMessage());
        }
    }
}
