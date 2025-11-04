package repaso_ariketa;

import repaso_ariketa.Bista.KontrolaBista;
import repaso_ariketa.Kontroler.KutxabankKontroler;
import repaso_ariketa.Modeloa.db.DBKutxabank;

public class Main {
    static void main(String[] args) {
        // MVC osagaiak sortu
        DBKutxabank eredua = new DBKutxabank();
        KontrolaBista ikuspegia = new KontrolaBista();
        // Kontrolatzailea sortu eta osagaiak injektatu (eredua + ikuspegia)
        KutxabankKontroler kontrolatzailea = new KutxabankKontroler(eredua, ikuspegia);
        // Exekuzioa hasi
        kontrolatzailea.exekutatu();
    }
}
