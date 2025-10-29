package SQLite;

import SQLite.Bista.KontsolaBista;
import SQLite.Kontroler.ZereginaKontroler;
import SQLite.Modeloa.db.DBZeregina;


public class Main {
    public static void main(String[] args) {
        // MVC osagaiak sortu
        DBZeregina eredua = new DBZeregina();
        KontsolaBista ikuspegia = new KontsolaBista();
        
        // Kontrolatzailea sortu eta osagaiak injektatu (eredua + ikuspegia)
        ZereginaKontroler kontrolatzailea = new ZereginaKontroler(eredua, ikuspegia);
        
        // Exekuzioa hasi
        kontrolatzailea.exekutatu();
    }
}