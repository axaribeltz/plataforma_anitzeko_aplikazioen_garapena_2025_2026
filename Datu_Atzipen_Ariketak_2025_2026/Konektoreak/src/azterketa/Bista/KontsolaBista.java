package azterketa.Bista;

import azterketa.Modeloa.Bezeroak;

import java.util.List;
import java.util.Scanner;

public class KontsolaBista {
    private Scanner irakurgailua = new Scanner(System.in);

    public int menuaErakutsi() {
        System.out.println("\n--- Bezero Kudeatzailea ---");
        System.out.println("1. Bezero guztiak ikusi");
        System.out.println("2. Bezero bat gehitu");
        System.out.println("3. Produktuak bistaratu izakinaren arabera");
        System.out.println("2. Irten");
        System.out.print("Aukeratu zenbaki bat: ");

        try {
            return Integer.parseInt(irakurgailua.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Aukera baliogabea
        }
    }

    public void bezeroakErakutsi(List<Bezeroak> bezeroak) {
        System.out.println("\n--- Bezero Zerrenda ---");
        if (bezeroak.isEmpty()) {
            System.out.println("Ez dago bezerorik");
            return;
        }
        for (Bezeroak z : bezeroak) {
            System.out.println(z);
        }
        System.out.println("-------------------------");
    }

    public String bezeroarenIDaEskatu() {
        System.out.print("Bezeroaren IDa sartu: ");
        return irakurgailua.nextLine();
    }

    public String bezeroarenIzenaEskatu() {
        System.out.print("Bezeroaren Izena sartu: ");
        return irakurgailua.nextLine();
    }

    public String bezeroarenHelbideaEskatu() {
        System.out.print("Bezeroaren Helbidea sartu: ");
        return irakurgailua.nextLine();
    }

    public String bezeroarenPostaKodeaEskatu() {
        System.out.print("Bezeroaren Posta Kodea sartu: ");
        return irakurgailua.nextLine();
    }

    public String bezeroarenTelefonoaEskatu() {
        System.out.print("Bezeroaren Telefonoa sartu: ");
        return irakurgailua.nextLine();
    }

    public int izakinaEskatu() {
        System.out.print("Produktu baten izakina sartu: ");
        try {
            return Integer.parseInt(irakurgailua.nextLine());
        } catch (NumberFormatException e) {
            mezuaErakutsi("Errorea: Mesedez, sartu zenbaki baliodun bat.");
            return -1;
        }
    }

    public void mezuaErakutsi(String mezua) {
        System.out.println("\n[INFO] " + mezua);
    }
}
