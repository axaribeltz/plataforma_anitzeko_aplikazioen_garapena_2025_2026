package bigarren_ariketa;

import java.sql.SQLException;
import java.util.Scanner;

import bigarren_ariketa.Models.Futbolista;
import bigarren_ariketa.Models.Taldea;
import bigarren_ariketa.Models.db.FutbolistaConnect;
import bigarren_ariketa.Models.db.TaldeaConnect;

public class FutbolAPP {
    public static void main(String[] args) throws SQLException {
        int aukera;
        FutbolistaConnect futcon = new FutbolistaConnect();
        TaldeaConnect talcon = new TaldeaConnect();
        Scanner sc = new Scanner(System.in);
        do {
            menuaErakutsi();
            aukera = Integer.parseInt(sc.nextLine());
            switch (aukera) {
                case 1 -> futbolariGuztiak(futcon);
                case 2 -> taldeGuztiak(talcon);
                case 3 -> futbolistaBerria(futcon, talcon);
                case 4 -> taldeBerria(talcon);
                case 5 -> {
                    System.out.println("Sartu jokalariaren DNI-a:");
                    int dni = Integer.parseInt(sc.nextLine());
                    System.out.println(futcon.getFutbolistaIDtik(dni));
                }
                case 6 -> {
                    System.out.println("Sartu taldearen ID-a:");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println(talcon.getTaldeaIDtik(id));
                }
                case 7 -> System.out.println("Amaitu da programa.");
                default -> System.out.println("Aukera okerra.");
            }
        } while (aukera != 7);
    }

    private static void taldeBerria(TaldeaConnect talcon) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu taldearen ID-a:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Sartu izena:");
        String izena = sc.nextLine();
        System.out.println("Sartu hiria:");
        String hiria = sc.nextLine();
        Taldea t = new Taldea(id, izena, hiria);
        talcon.taldeaSortu(t);
    }

    private static void futbolistaBerria(FutbolistaConnect futcon, TaldeaConnect talcon) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sartu futbolistaren DNI-a:");
        int dni = Integer.parseInt(sc.nextLine());
        System.out.println("Sartu izena:");
        String izena = sc.nextLine();
        System.out.println("Sartu abizena:");
        String abizena = sc.nextLine();
        System.out.println("Sartu soldata:");
        double soldata = Double.parseDouble(sc.nextLine());
        System.out.println("Sartu taldearen ID-a:");
        int idTaldea = Integer.parseInt(sc.nextLine());
        Futbolista f = new Futbolista(dni, izena, abizena, soldata, idTaldea);
        futcon.futbolistaSortu(f);
    }

    private static void taldeGuztiak(TaldeaConnect talcon) throws SQLException {
        for (Taldea t : talcon.getTaldeak()) System.out.println(t);
    }

    private static void futbolariGuztiak(FutbolistaConnect futcon) throws SQLException {
        for (Futbolista f : futcon.getFutbolistak()) System.out.println(f);
    }

    private static void menuaErakutsi() {
        System.out.println("Ondo etorria futbolarien DDBBra!");
        System.out.println("1- Jokalari guztiak ikusi");
        System.out.println("2- Taldeak ikusi");
        System.out.println("3- Jokalari bat gehitu");
        System.out.println("4- Talde bat gehitu");
        System.out.println("5- Jokalaria erakutsi DNI-a emanda");
        System.out.println("6- Taldea erakutsi ID-a emanda");
        System.out.println("7- Irten");
        System.out.println("Idatzi zure erantzuna:");
    }
}
