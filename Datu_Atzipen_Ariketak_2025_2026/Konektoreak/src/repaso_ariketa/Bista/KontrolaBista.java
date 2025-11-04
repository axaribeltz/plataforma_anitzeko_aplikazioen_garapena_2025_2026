package repaso_ariketa.Bista;

import repaso_ariketa.Modeloa.ErabiltzaileMugimenduak;

import java.util.List;
import java.util.Scanner;

public class KontrolaBista {
    private Scanner irakurgailua = new Scanner(System.in);

    public int menuaErakutsi() {
        System.out.println("\n--- Zeregin Kudeatzailea ---");
        System.out.println("1. Erabiltzaile mugimendu guztiak ikusi");
        //System.out.println("2. Zeregina gehitu");
        //System.out.println("3. Zeregina burututzat markatu");
        System.out.println("2. Irten");
        System.out.print("Aukeratu zenbaki bat: ");

        try {
            return Integer.parseInt(irakurgailua.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Aukera baliogabea
        }
    }

    public void erabiltzaileMugimenduakErakutsi(List<ErabiltzaileMugimenduak> erabiltzaileMugimenduak) {
        System.out.println("\n--- Erabiltzaile Mugimendu Zerrenda ---");
        if (erabiltzaileMugimenduak.isEmpty()) {
            System.out.println("Ez mugimendurik.");
            return;
        }
        for (ErabiltzaileMugimenduak z : erabiltzaileMugimenduak) {
            System.out.println(z);
        }
        System.out.println("-------------------------");
    }

    public void mezuaErakutsi(String mezua) {
        System.out.println("\n[INFO] " + mezua);
    }
}
