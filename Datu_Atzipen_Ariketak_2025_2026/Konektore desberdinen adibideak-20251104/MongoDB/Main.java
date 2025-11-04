public class Main {

    public static void main(String[] args) {
        
        // try-with-resources erabiltzen dugu.
        // ErabiltzaileaDB-k AutoCloseable inplementatzen duenez, konexioa bakarrik itxiko da.
        try (ErabiltzaileaDB db = new ErabiltzaileaDB("localhost", 27017, "nireDatuBasea", "erabiltzaileak")) {

            // C) CREATE (Sortu)
            System.out.println("\n--- SORTZEN ---");
            Erabiltzailea ana = new Erabiltzailea("Ana Gómez", 25, "Bartzelona");
            db.sortu(ana);
            
            Erabiltzailea luis = new Erabiltzailea("Luis Rivas", 40, "Sevilla");
            db.sortu(luis);

            // R) READ (Irakurri)
            System.out.println("\n--- IRAKURTZEN ---");
            Erabiltzailea aurkitutakoAna = db.izenezBilatu("Ana Gómez");
            if (aurkitutakoAna != null) {
                System.out.println("Aurkitua: " + aurkitutakoAna);
            }

            // U) UPDATE (Eguneratu)
            System.out.println("\n--- EGUNERATZEN ---");
            db.adinaEguneratu("Ana Gómez", 26);
            Erabiltzailea eguneratutakoAna = db.izenezBilatu("Ana Gómez");
            System.out.println("Eguneratua: " + eguneratutakoAna);

            // D) DELETE (Ezabatu)
            System.out.println("\n--- EZABATZEN ---");
            db.ezabatu("Luis Rivas");
            System.out.println("Luis Rivas ezabatuta.");
            
            Erabiltzailea ezabatutakoLuis = db.izenezBilatu("Luis Rivas");
            System.out.println("Luisek DB-an jarraitzen du? " + (ezabatutakoLuis == null ? "Ez" : "Bai"));

        } catch (Exception e) {
            System.err.println("Errorea aplikazioan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}