package Ariketa_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class IstorioakBatu {
    public static void main(String[] args) {
        try (
                BufferedReader ist1 = new BufferedReader(new FileReader("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\Ariketa_2\\istorioa_zatia1.txt"));
                BufferedReader ist2 = new BufferedReader(new FileReader("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\Ariketa_2\\istorioa_zatia2.txt"));
                BufferedReader ist3 = new BufferedReader(new FileReader("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\Ariketa_2\\istorioa_zatia3.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\Ariketa_2\\istoria_osoa.txt"))
        ) {
            String line;

            //Lehen fitxategia idatzi
            while ((line = ist1.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            //Bigarren fitxategia idatzi
            while ((line = ist2.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            //Hirugarren fitxategia idatzi
            while ((line = ist3.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Hiru fitxategiak bateratu dira");

        } catch (Exception e) {
            System.out.println("Errorea fitxategiak bateratzean: " + e.getMessage());
        }
    }
}
