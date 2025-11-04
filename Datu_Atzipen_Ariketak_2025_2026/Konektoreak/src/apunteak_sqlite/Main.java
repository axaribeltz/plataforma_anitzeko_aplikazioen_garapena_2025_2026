package apunteak_sqlite;

import apunteak_sqlite.Bista.KontsolaBista;
import apunteak_sqlite.Kontroler.ZereginaKontroler;


public class Main {
    public static void main(String[] args) {
        // MVC osagaiak sortu
        apunteak_sqlite.Modeloa.db.DBZeregina eredua = new apunteak_sqlite.Modeloa.db.DBZeregina();
        KontsolaBista ikuspegia = new KontsolaBista();
        
        // Kontrolatzailea sortu eta osagaiak injektatu (eredua + ikuspegia)
        ZereginaKontroler kontrolatzailea = new ZereginaKontroler(eredua, ikuspegia);
        
        // Exekuzioa hasi
        kontrolatzailea.exekutatu();
    }
}