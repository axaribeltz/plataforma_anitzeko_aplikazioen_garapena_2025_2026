package azterketa;

import azterketa.Bista.KontsolaBista;
import azterketa.Kontroler.BiltegiaKontroler;
import azterketa.Modeloa.db.DBBiltegia;

public class Main {
    static void main(String[] args) {
        // MVC osagaiak lortu
        DBBiltegia eredua = new DBBiltegia();
        KontsolaBista ikuspegia = new KontsolaBista();
        // Kontrolatzailea sortu eta osagaiak injektatu (eredua + ikuspegia)
        BiltegiaKontroler kontrolatzailea = new BiltegiaKontroler(eredua, ikuspegia);
        // Exekuzioa hasi
        kontrolatzailea.exekutatu();
    }
}
