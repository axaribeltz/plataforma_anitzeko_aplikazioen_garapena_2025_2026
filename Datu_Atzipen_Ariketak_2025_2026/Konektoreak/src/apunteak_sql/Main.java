package apunteak_sql;

import apunteak_sql.Bista.KontsolaBista;
import apunteak_sql.Kontroler.ZereginaKontroler;
import apunteak_sql.Modeloa.db.DBZeregina;


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