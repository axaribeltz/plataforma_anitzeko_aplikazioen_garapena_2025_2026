package ErrepasoAriketak.a05_Jokalariak;

import ErrepasoAriketak.a05_Jokalariak.model.Atezaina;
import ErrepasoAriketak.a05_Jokalariak.model.Aurrelaria;
import ErrepasoAriketak.a05_Jokalariak.model.Defentsa;
import ErrepasoAriketak.a05_Jokalariak.model.Jokalaria;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JokatuApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Jokalaria jok1 = new Aurrelaria("Agirretxe", 1.82, 9);
        Jokalaria jok2 = new Atezaina("Arkonada", 1.89, 1);
        Jokalaria jok3 = new Defentsa("Zubeldia", 1.80, 5);

        ArrayList<Jokalaria> jokalariZerrenda = new ArrayList<>();
        jokalariZerrenda.add(jok1);
        jokalariZerrenda.add(jok2);
        jokalariZerrenda.add(jok3);

        Scanner irakurgailua = new Scanner(System.in);
        int aukera = 0;

        do {
            menuaErakutsi();
            try {
                aukera = Integer.parseInt(irakurgailua.nextLine());
                switch (aukera) {
                    case 1:
                        //Sortu jokalari berria eta ArrayList-an sartu
                        sortuJokalaria(jokalariZerrenda);
                        break;
                    case 2:
                        //Idatzi bitar fitxategi batean
                        idatziBitarrean(jokalariZerrenda);
                        break;
                    case 3:
                        //Irakurri fitxategi bitarra
                        irakurriBitarrean();
                        break;
                    case 4:
                        //Jokalari mota kopuru zenbatu
                        kontatuPosizioBakoitzekoJokalariak();
                        break;
                    case 5:
                        System.out.println("Programatik irteten. Ikusi arte!");
                        break;
                    default:
                        System.out.println("Aukera okerra. Mesedez, saiatu berriro.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Sarrera okerra. Mesedez, sartu zenbaki bat.");
            }
        } while (aukera != 5);

        irakurgailua.close();
    }

    public static void menuaErakutsi() {
        System.out.println("\n--- Jokalari Kudeaketa Menua ---");
        System.out.println("1. Jokalari berri bat sortu");
        System.out.println("2. Jokalariak gorde");
        System.out.println("3. Jokalariak ikusi");
        System.out.println("4. Jokalari mota desberdinak zenbatu");
        System.out.println("5. Irten");
        System.out.print("Aukeratu bat: ");
    }

    public static void idatziBitarrean(ArrayList<Jokalaria> jokalariZerrenda) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a05_Jokalariak/Jokalariak.dat"));
        output.writeObject(jokalariZerrenda);
        output.flush();
        output.close();

        System.out.println("Jokalaria gorde da!");
    }

    public static void irakurriBitarrean() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a05_Jokalariak/Jokalariak.dat"));

        ArrayList<Jokalaria> jokalariZerrenda = (ArrayList<Jokalaria>) input.readObject();
        input.close();

        System.out.println("------ JOKALARI ZERRENDA ------");
        for (Jokalaria jok : jokalariZerrenda) {
            System.out.println(jok);
        }

        for (int i = 0; i < jokalariZerrenda.size(); i++) {
            Jokalaria jok = jokalariZerrenda.get(i);
            System.out.println("JOKALARIA #" + (i + 1));
            System.out.println("--------------------------------");
            System.out.println("Izena: " + jok.getIzena());
            System.out.println("Altuera: " + jok.getAltuera() + " m");
            System.out.println("Zenbakia: " + jok.getZenbakia());
            System.out.println("--------------------------------");
        }
    }

    public static void sortuJokalaria(ArrayList<Jokalaria> jokalariZerrenda) {
        Scanner irakurgailua = new Scanner(System.in);
        int aukera = 0;

        System.out.println("\n--- Aukeratu jokalari mota ---");
        System.out.println("1. Aurrelaria");
        System.out.println("2. Atezaina");
        System.out.println("3. Defentsa");
        System.out.println("4. Irten");
        System.out.print("Aukeratu bat: ");

        try {
            aukera = Integer.parseInt(irakurgailua.nextLine());
            String izena;
            double altuera;
            int zenbakia;

            switch (aukera) {
                case 1:
                    //Sortu aurrelaria
                    System.out.print("Sartu aurrelariaren izena: ");
                    izena = irakurgailua.nextLine();

                    System.out.print("Sartu aurrelariaren altuera: ");
                    altuera = Double.parseDouble(irakurgailua.nextLine());

                    System.out.print("Sartu aurrelariaren zenbakia: ");
                    zenbakia = Integer.parseInt(irakurgailua.nextLine());

                    jokalariZerrenda.add(new Aurrelaria(izena, altuera, zenbakia));
                    System.out.println("Aurrelaria sortu da!");
                    break;
                case 2:
                    //Sortu atezaina
                    System.out.print("Sartu atezainaren izena: ");
                    izena = irakurgailua.nextLine();

                    System.out.print("Sartu atezainaren altuera: ");
                    altuera = Double.parseDouble(irakurgailua.nextLine());

                    System.out.print("Sartu atezainaren zenbakia: ");
                    zenbakia = Integer.parseInt(irakurgailua.nextLine());

                    jokalariZerrenda.add(new Atezaina(izena, altuera, zenbakia));
                    System.out.println("Atezaina sortu da!");
                    break;
                case 3:
                    //Sortu defentsa
                    System.out.print("Sartu defentsaren izena: ");
                    izena = irakurgailua.nextLine();

                    System.out.print("Sartu defentsaren altuera: ");
                    altuera = Double.parseDouble(irakurgailua.nextLine());

                    System.out.print("Sartu defentsaren zenbakia: ");
                    zenbakia = Integer.parseInt(irakurgailua.nextLine());

                    jokalariZerrenda.add(new Defentsa(izena, altuera, zenbakia));
                    System.out.println("Defentsa sortu da!");
                    break;
                case 4:
                    System.out.println("Programatik irteten. Ikusi arte!");
                    break;
                default:
                    System.out.println("Aukera okerra. Mesedez, saiatu berriro.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Sarrera okerra. Mesedez, sartu zenbaki bat.");
        }
    }

    public static void kontatuPosizioBakoitzekoJokalariak() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a05_Jokalariak/Jokalariak.dat"));

        ArrayList<Jokalaria> jokalariZerrenda = (ArrayList<Jokalaria>) input.readObject();
        input.close();

        int aurrelariak = 0, atezainak = 0, defentsak = 0;
        for (Jokalaria jok : jokalariZerrenda) {
            if (jok instanceof Aurrelaria) {
                aurrelariak++;
            } else if (jok instanceof Defentsa) {
                defentsak++;
            } else {
                atezainak++;
            }
        }

        System.out.println("------ JOKALARI MOTA KOPURU ZERRENDA ------");
        System.out.println("Aurrelari kopurua jolasten: " + aurrelariak);
        System.out.println("Atezain kopurua jolasten: " + atezainak);
        System.out.println("Defentsa kopurua jolasten: " + defentsak);
    }
}
