package azterketa.Kontroler;

import azterketa.Bista.KontsolaBista;
import azterketa.Modeloa.db.DBBiltegia;

public class BiltegiaKontroler {
    private DBBiltegia eredua;
    private KontsolaBista ikuspegia;

    public BiltegiaKontroler(DBBiltegia eredua, KontsolaBista ikuspegia) {
        super();
        this.eredua = eredua;
        this.ikuspegia = ikuspegia;
    }

    public void exekutatu() {
        int aukera;
        do {
            aukera = ikuspegia.menuaErakutsi();

            switch (aukera) {
                case 1:
                    bezeroakIkusi();
                    break;
                case 2:
                    bezeroaGehitu();
                    break;
                case 3:
                    bilatuProduktuakIzakinarenArabera();
                    break;
                case 4:
                    ikuspegia.mezuaErakutsi("Aplikaziotik irtetzen.");
                    break;
                default:
                    ikuspegia.mezuaErakutsi("Aukera baliogabea. Saiatu berriro.");
            }
        } while (aukera != 4);
    }

    private void bezeroakIkusi() {
        ikuspegia.bezeroakErakutsi(eredua.bezeroakLortu());
    }

    private void bezeroaGehitu() {
        String id = ikuspegia.bezeroarenIDaEskatu();
        String izena = ikuspegia.bezeroarenIzenaEskatu();
        String helbidea = ikuspegia.bezeroarenHelbideaEskatu();
        String postaKodea = ikuspegia.bezeroarenPostaKodeaEskatu();
        String telefonoa = ikuspegia.bezeroarenTelefonoaEskatu();

        if (id != null && !id.trim().isEmpty() && izena!= null && !izena.trim().isEmpty() && helbidea!= null && !helbidea.trim().isEmpty() && postaKodea!= null && !postaKodea.trim().isEmpty() && telefonoa!= null && !telefonoa.trim().isEmpty()) {
            eredua.bezeroaGehitu(id, izena, helbidea, postaKodea, telefonoa);
            ikuspegia.mezuaErakutsi("Bezeroa ondo gehitu da.");
        } else {
            ikuspegia.mezuaErakutsi("Bezeroaren daturen bat hutsik utzi duzu");
        }
    }

    private void bilatuProduktuakIzakinarenArabera() {
        int izakina = ikuspegia.izakinaEskatu();
        if (izakina > 0) {
            eredua.bilatuProduktuakIzakinarenArabera(izakina);
            ikuspegia.mezuaErakutsi(izakina + " Ikazina ondo aurkitu da");
        }
    }
}
