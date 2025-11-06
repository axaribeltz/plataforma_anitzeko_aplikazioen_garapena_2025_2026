package azterketa.Modeloa;

public class Produktuak {
    int id;
    String izena;
    String hornitzailea;
    double prezioa;
    int izakinak;

    public Produktuak(int id, String izena, String hornitzailea, double prezioa, int izakinak) {
        this.id = id;
        this.izena = izena;
        this.hornitzailea = hornitzailea;
        this.prezioa = prezioa;
        this.izakinak = izakinak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getHornitzailea() {
        return hornitzailea;
    }

    public void setHornitzailea(String hornitzailea) {
        this.hornitzailea = hornitzailea;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public int getIzakinak() {
        return izakinak;
    }

    public void setIzakinak(int izakinak) {
        this.izakinak = izakinak;
    }

    @Override
    public String toString() {
        return "Produktuak{" +
                "id=" + id +
                ", izena='" + izena + '\'' +
                ", hornitzailea='" + hornitzailea + '\'' +
                ", prezioa=" + prezioa +
                ", izakinak=" + izakinak +
                '}';
    }
}
