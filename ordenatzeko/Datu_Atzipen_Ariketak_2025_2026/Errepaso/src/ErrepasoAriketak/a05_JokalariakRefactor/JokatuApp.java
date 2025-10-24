package ErrepasoAriketak.a05_JokalariakRefactor;

import ErrepasoAriketak.a05_JokalariakRefactor.model.Atezaina;
import ErrepasoAriketak.a05_JokalariakRefactor.model.Aurrelaria;
import ErrepasoAriketak.a05_JokalariakRefactor.model.Defentsa;
import ErrepasoAriketak.a05_JokalariakRefactor.model.Jokalaria;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * JOKATU APLIKAZIOA
 * ----------------
 * Programa honek jokalariak kudeatzen ditu: sortu, gorde, irakurri eta kontatu.
 * Fitxategi bitar batean gordetzen dira (serializazio bidez).
 */
public class JokatuApp {

    // Fitxategiaren kokapena (bide absolutua)
    private static final String FITXATEGI_BIDEA =
            "/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a05_Jokalariak/Jokalariak.dat";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Hasierako hiru jokalariak
        ArrayList<Jokalaria> jokalariZerrenda = new ArrayList<>();
        jokalariZerrenda.add(new Aurrelaria("Agirretxe", 1.82, 9));
        jokalariZerrenda.add(new Atezaina("Arkonada", 1.89, 1));
        jokalariZerrenda.add(new Defentsa("Zubeldia", 1.80, 5));

        Scanner irakurgailua = new Scanner(System.in);
        int aukera;

        // Menu nagusia: erabiltzaileak 5 aukeratu arte
        do {
            erakutsiMenua();
            try {
                aukera = Integer.parseInt(irakurgailua.nextLine());

                switch (aukera) {
                    case 1 -> sortuJokalaria(jokalariZerrenda); // Jokalari berria sortu
                    case 2 -> idatziFitxategian(jokalariZerrenda); // Zerrenda fitxategian gorde
                    case 3 -> irakurriFitxategitik(); // Fitxategiko datuak erakutsi
                    case 4 -> zenbatuPosizioak(); // Posizio bakoitzeko jokalari kopurua erakutsi
                    case 5 -> erakutsiJokalarienJokatu(); // Posizio bakoitzeko jokalari kopurua erakutsi
                    case 6 -> System.out.println("Programa amaitzen... Agur!");
                    default -> System.out.println("Aukera okerra. Saiatu berriro.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Errorea: sarrera ez da zenbaki bat.");
                aukera = 0; // Segurtasunagatik, bueltatzeko menura
            }

        } while (aukera != 6);

        irakurgailua.close();
    }

    /**
     * Menu nagusia kontsolan erakusten du.
     */
    public static void erakutsiMenua() {
        System.out.println("\n--- JOKALARI KUDEAKETA MENUA ---");
        System.out.println("1. Jokalari berria sortu");
        System.out.println("2. Jokalariak fitxategian gorde");
        System.out.println("3. Fitxategiko jokalariak erakutsi");
        System.out.println("4. Posizio bakoitzeko jokalari kopurua zenbatu");
        System.out.println("5. Erakutsi zer egiten ari diren jokalariak");
        System.out.println("6. Irten");
        System.out.print("Aukeratu bat: ");
    }

    /**
     * Jokalari zerrenda fitxategi bitar batean gordetzen du (serializazioa).
     */
    public static void idatziFitxategian(ArrayList<Jokalaria> jokalariZerrenda) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FITXATEGI_BIDEA))) {
            output.writeObject(jokalariZerrenda);
            System.out.println("✅ Jokalariak fitxategian gorde dira!");
        } catch (IOException e) {
            System.out.println("❌ Errorea fitxategia idaztean: " + e.getMessage());
        }
    }

    /**
     * Fitxategi bitarra irakurtzen du eta jokalariak pantailaratzen ditu.
     */
    public static void irakurriFitxategitik() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FITXATEGI_BIDEA))) {

            ArrayList<Jokalaria> jokalariZerrenda = (ArrayList<Jokalaria>) input.readObject();

            System.out.println("\n------ JOKALARI ZERRENDA ------");
            for (int i = 0; i < jokalariZerrenda.size(); i++) {
                Jokalaria jok = jokalariZerrenda.get(i);
                System.out.printf("JOKALARIA #%d%n", i + 1);
                System.out.println("--------------------------------");
                System.out.println("Izena: " + jok.getIzena());
                System.out.println("Altuera: " + jok.getAltuera() + " m");
                System.out.println("Zenbakia: " + jok.getZenbakia());
                System.out.println("--------------------------------\n");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    /**
     * Jokalari berri bat sortzen du eta zerrendara gehitzen du.
     */
    public static void sortuJokalaria(ArrayList<Jokalaria> jokalariZerrenda) {
        Scanner irakurgailua = new Scanner(System.in);

        System.out.println("\n--- Aukeratu jokalari mota ---");
        System.out.println("1. Aurrelaria");
        System.out.println("2. Atezaina");
        System.out.println("3. Defentsa");
        System.out.print("Aukeratu bat: ");

        try {
            int mota = Integer.parseInt(irakurgailua.nextLine());
            System.out.print("Sartu izena: ");
            String izena = irakurgailua.nextLine();

            System.out.print("Sartu altuera (m): ");
            double altuera = Double.parseDouble(irakurgailua.nextLine());

            System.out.print("Sartu zenbakia: ");
            int zenbakia = Integer.parseInt(irakurgailua.nextLine());

            Jokalaria berria = switch (mota) {
                case 1 -> new Aurrelaria(izena, altuera, zenbakia);
                case 2 -> new Atezaina(izena, altuera, zenbakia);
                case 3 -> new Defentsa(izena, altuera, zenbakia);
                default -> null;
            };

            if (berria != null) {
                jokalariZerrenda.add(berria);
                System.out.println("✅ Jokalaria gehitu da!");
            } else {
                System.out.println("❌ Aukera okerra, ez da jokalaririk sortu.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Sarrera okerra. Saiatu berriro.");
        }
    }

    /**
     * Fitxategiko jokalariak irakurri eta posizio bakoitzeko kopurua zenbatzen du.
     */
    public static void zenbatuPosizioak() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FITXATEGI_BIDEA))) {

            ArrayList<Jokalaria> jokalariZerrenda = (ArrayList<Jokalaria>) input.readObject();

            int aurrelariak = 0, atezainak = 0, defentsak = 0;

            for (Jokalaria jok : jokalariZerrenda) {
                if (jok instanceof Aurrelaria) aurrelariak++;
                else if (jok instanceof Atezaina) atezainak++;
                else if (jok instanceof Defentsa) defentsak++;
            }

            System.out.println("\n------ POSIZIOAREN ARABERAKO KOPURUA ------");
            System.out.println("Aurrelariak: " + aurrelariak);
            System.out.println("Atezainak: " + atezainak);
            System.out.println("Defentsak: " + defentsak);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    public static void erakutsiJokalarienJokatu() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FITXATEGI_BIDEA))) {

            ArrayList<Jokalaria> jokalariZerrenda = (ArrayList<Jokalaria>) input.readObject();

            for (Jokalaria jok : jokalariZerrenda) {
                System.out.println("\n" + jok.getIzena() + " ekintza egiten ari da:");
                jok.jokatu();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }
}
