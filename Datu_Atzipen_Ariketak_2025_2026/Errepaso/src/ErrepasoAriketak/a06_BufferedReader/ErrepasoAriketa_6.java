package ErrepasoAriketak.a06_BufferedReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ErrepasoAriketa_6 {
    public static void main(String[] args) {
        Scanner irakurgailua = new Scanner(System.in);
        int aukera;

        do {
            erakutsiMenua();
            try {
                aukera = Integer.parseInt(irakurgailua.nextLine());

                switch (aukera) {
                    case 1 -> irakurriTextua();
                    case 2 -> aldatuMinusMaius();
                    case 3 -> aldatuLetrak();
                    case 4 -> System.out.println("Programa amaitzen... Agur!");
                    default -> System.out.println("Aukera okerra. Saiatu berriro.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Errorea: sarrera ez da zenbaki bat.");
                aukera = 0;
            }

        } while (aukera != 4);
        irakurgailua.close();
    }

    // 🔹 Fitxategiaren path-a konstante moduan
    private static final String FITXATEGIA = "/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a06_BufferedReader/Komintern.txt";

    /**
     * Menu nagusia erakusten du
     */
    public static void erakutsiMenua() {
        System.out.println("\n--- TEXTUAREN KUDEAKETA MENUA ---");
        System.out.println("1. Irakurri testua");
        System.out.println("2. Aldatu minuskulak maiuskulangatik eta alderantziz");
        System.out.println("3. Aldatu letrak: 'a' -> '?'");
        System.out.println("4. Irten");
        System.out.print("Aukeratu bat: ");
    }

    /**
     * Fitxategia irakurtzen du eta pantailan erakusten du
     */
    public static void irakurriTextua() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FITXATEGIA))) {
            String line;
            System.out.println("\n--- FITXATEGIAREN EDUKIA ---");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("❌ Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    /**
     * Testuaren minuskulak eta maiuskulak trukatzen ditu eta fitxategia eguneratzen du
     */
    public static void aldatuMinusMaius() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(FITXATEGIA))
        ) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                StringBuilder swapped = new StringBuilder();

                for (char c : line.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        swapped.append(Character.toLowerCase(c));
                    } else if (Character.isLowerCase(c)) {
                        swapped.append(Character.toUpperCase(c));
                    } else {
                        swapped.append(c);
                    }
                }
                lines.add(swapped.toString());
            }

            // 🟢 Fitxategia eguneratu (sobrescribir)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FITXATEGIA, false))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
                System.out.println("✅ Fitxategia eguneratu da: maiuskulak/minuskulak trukatuta.");
            }

        } catch (Exception e) {
            System.out.println("❌ Errorea fitxategia prozesatzean: " + e.getMessage());
        }
    }

    /**
     * Fitxategiko 'a' letrak '?' bihurtzen ditu eta fitxategia eguneratzen du
     */
    public static void aldatuLetrak() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FITXATEGIA))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("a", "?");
                lines.add(line);
            }

            // 🟢 Fitxategia eguneratu
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FITXATEGIA, false))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
                System.out.println("✅ Fitxategia eguneratu da: 'a' -> '?' bihurtuta.");
            }

        } catch (Exception e) {
            System.out.println("❌ Errorea fitxategia prozesatzean: " + e.getMessage());
        }
    }

    /**
     * Bi fitxategi desberdin irakurtzen ditu, edukia bateratzen du eta fitxategi berrian gordetzen du.
     */
    public static void batuBiFitxategiak(String fitx1, String fitx2, String fitxBerria) {
        try (
                BufferedReader br1 = new BufferedReader(new FileReader(fitx1));
                BufferedReader br2 = new BufferedReader(new FileReader(fitx2));
                BufferedWriter writer = new BufferedWriter(new FileWriter(fitxBerria))
        ) {
            String line;

            // 🔹 Lehen fitxategia idatzi
            while ((line = br1.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            // 🔹 Bigarren fitxategia idatzi
            while ((line = br2.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("✅ Bi fitxategiak bateratu dira eta " + fitxBerria + " fitxategian gordeta daude.");

        } catch (Exception e) {
            System.out.println("❌ Errorea fitxategiak bateratzean: " + e.getMessage());
        }
    }
}
