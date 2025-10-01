package ErrepasoAriketak;

import java.io.*;
import java.util.Scanner;

public class ErrepasoAriketa_2 {
    public static void main(String[] args) {
        Scanner irakurgailua = new Scanner(System.in);
        int aukera = 0;

        do {
            menuaErakutsi();
            try {
                aukera = Integer.parseInt(irakurgailua.nextLine());
                switch (aukera) {
                    case 1:
                        pertsonaSortu(irakurgailua);
                        break;
                    case 2:
                        pertsonaBistaratu();
                        break;
                    case 3:
                        System.out.println("Programatik irteten. Ikusi arte!");
                        break;
                    default:
                        System.out.println("Aukera okerra. Mesedez, saiatu berriro.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Sarrera okerra. Mesedez, sartu zenbaki bat.");
            }
        } while (aukera != 3);
        irakurgailua.close();
    }

    private static void menuaErakutsi() {
        System.out.println("\n--- Datuen Kudeaketa Menua ---");
        System.out.println("1. Sortu pertsona baten profila");
        System.out.println("2. Profila bistaratu");
        System.out.println("3. Irten");
        System.out.print("Aukeratu bat: ");
    }

    private static void pertsonaSortu(Scanner irakurgailua) {
        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Errepaso\\src\\ErrepasoAriketak\\pertsona.dat", "rw")) {
            System.out.println("\n--- Pertsona Berria Sortu RandomAccess ---");

            // Punteroa fitxategiaren amaierara mugitu, erregistro berria gehitzeko
            raf.seek(raf.length());

            System.out.print("Sartu izena: ");
            String izena = irakurgailua.nextLine();

            System.out.print("Sartu abizena: ");
            String abizena = irakurgailua.nextLine();

            System.out.print("Sartu altuera: ");
            double altuera = Double.parseDouble(irakurgailua.nextLine());

            // Datuak fitxategi bitarrean idatzi
            raf.writeUTF(izena);
            raf.writeUTF(abizena);
            raf.writeDouble(altuera);

            //Datuak txt fitxategian idatzi
            Pertsona p1= new Pertsona(izena, abizena, altuera);
            File file = new File(
                    "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Errepaso\\src\\ErrepasoAriketak\\pertsona.txt"
            );

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(p1.getIzena() + "," + p1.getAbizena() + "," + p1.getAltuera());
                bufferedWriter.newLine();
            }

            System.out.println("Pertsona ondo gorde da.");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Errorea pertsona gordetzean: " + e.getMessage());
        }
    }


    private static void pertsonaBistaratu() {
        try (RandomAccessFile raf = new RandomAccessFile(
                "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Errepaso\\src\\ErrepasoAriketak\\pertsona.dat",
                "rw")) {

            System.out.println("\n--- Pertsona Zerrenda (DAT fitxategitik) ---");

            if (raf.length() == 0) {
                System.out.println("Ez dago pertsonarik erregistratuta.");
            } else {
                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {
                    String izena = raf.readUTF();
                    String abizena = raf.readUTF();
                    double altuera = raf.readDouble();

                    System.out.println(izena +","+ abizena +","+ altuera);
                }
            }

            // Orain TXT fitxategia irakurri
            File file = new File(
                    "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Errepaso\\src\\ErrepasoAriketak\\pertsona.txt"
            );

            if (file.exists()) {
                System.out.println("\n--- Pertsona Zerrenda (TXT fitxategitik) ---");
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("\nEz da aurkitu TXT fitxategirik.");
            }

        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }
}

