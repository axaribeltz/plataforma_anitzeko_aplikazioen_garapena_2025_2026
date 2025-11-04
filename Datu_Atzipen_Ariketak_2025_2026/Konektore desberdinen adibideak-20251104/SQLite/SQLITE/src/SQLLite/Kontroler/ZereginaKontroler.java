package SQLLite.Kontroler;

import SQLLite.Bista.KontsolaBista;
import SQLLite.Modeloa.db.DBZeregina;

public class ZereginaKontroler {
    private DBZeregina eredua;
    private KontsolaBista ikuspegia;

    public ZereginaKontroler(DBZeregina eredua, KontsolaBista ikuspegia) {
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
                    zereginakIkusi();
                    break;
                case 2:
                    zereginaGehitu();
                    break;
                case 3:
                    zereginaBurutu();
                    break;
                case 4:
                    ikuspegia.mezuaErakutsi("Aplikaziotik irtetzen.");
                    break;
                default:
                    ikuspegia.mezuaErakutsi("Aukera baliogabea. Saiatu berriro.");
            }
        } while (aukera != 4);
    }

    private void zereginakIkusi() {
        ikuspegia.zereginakErakutsi(eredua.zereginGuztiakLortu());
    }

    private void zereginaGehitu() {
        String deskribapena = ikuspegia.zereginBerriaEskatu();
        if (deskribapena != null && !deskribapena.trim().isEmpty()) {
            eredua.zereginaGehitu(deskribapena);
            ikuspegia.mezuaErakutsi("Zeregina ondo gehitu da.");
        } else {
            ikuspegia.mezuaErakutsi("Deskribapena ezin da hutsik egon.");
        }
    }

    private void zereginaBurutu() {
        zereginakIkusi();
        
        int zereginId = ikuspegia.zereginIdEskatu();
        if (zereginId > 0) {
            eredua.burututzatMarkatu(zereginId);
            ikuspegia.mezuaErakutsi(zereginId + " ID-a duen zeregina burututzat markatu da.");
        }
    }
}