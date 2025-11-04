package repaso_ariketa.Kontroler;

import repaso_ariketa.Bista.KontrolaBista;
import repaso_ariketa.Modeloa.db.DBKutxabank;

public class KutxabankKontroler {
    private DBKutxabank eredua;
    private KontrolaBista ikuspegia;

    public KutxabankKontroler(DBKutxabank eredua, KontrolaBista ikuspegia) {
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
                    erabiltzaileMugimenduakIkusi();
                    break;
                case 2:
                    ikuspegia.mezuaErakutsi("Aplikaziotik irtetzen.");
                    break;
                default:
                    ikuspegia.mezuaErakutsi("Aukera baliogabea. Saiatu berriro.");
            }
        } while (aukera != 2);
    }

    private void erabiltzaileMugimenduakIkusi() {
        ikuspegia.erabiltzaileMugimenduakErakutsi(eredua.erabiltzaileMugimenduakLortu());
    }

}
