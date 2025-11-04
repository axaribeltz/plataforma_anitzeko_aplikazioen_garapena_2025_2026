package apunteak_sql.Bista;

import java.util.List;
import java.util.Scanner;

import apunteak_sql.Modeloa.Zeregina;


public class KontsolaBista {
    private Scanner irakurgailua = new Scanner(System.in);

    public int menuaErakutsi() {
        System.out.println("\n--- Zeregin Kudeatzailea ---");
        System.out.println("1. Zereginak ikusi");
        System.out.println("2. Zeregina gehitu");
        System.out.println("3. Zeregina burututzat markatu");
        System.out.println("4. Irten");
        System.out.print("Aukeratu zenbaki bat: ");
        
        try {
            return Integer.parseInt(irakurgailua.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Aukera baliogabea
        }
    }

    public void zereginakErakutsi(List<Zeregina> zereginak) {
        System.out.println("\n--- Zeregin Zerrenda ---");
        if (zereginak.isEmpty()) {
            System.out.println("Ez dago zereginik.");
            return;
        }
        for (Zeregina z : zereginak) {
            System.out.println(z);
        }
        System.out.println("-------------------------");
    }

    public String zereginBerriaEskatu() {
        System.out.print("Sartu zeregin berriaren deskribapena: ");
        return irakurgailua.nextLine();
    }

    public int zereginIdEskatu() {
        System.out.print("Sartu zereginaren ID-a: ");
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