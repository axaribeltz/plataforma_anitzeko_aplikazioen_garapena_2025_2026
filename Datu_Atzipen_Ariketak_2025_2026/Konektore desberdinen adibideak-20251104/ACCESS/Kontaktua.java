package Model;

public class Kontaktua {
    private int id;
    private String izena; 
    private int adina;   

    // Eraikitzailea (Constructor)
    public Kontaktua(int id, String izena, int adina) {
        this.id = id;
        this.izena = izena;
        this.adina = adina;
    }

    // Getterrak (Datuak lortzeko metodoak)
    public int getId() {
        return id;
    }

    public String getIzena() {
        return izena;
    }

    public int getAdina() {
        return adina;
    }

    // toString Metodoa inprimatzeko
    @Override
    public String toString() {
        return "ID: " + id + ", Izena: " + izena + ", Adina: " + adina;
    }
}