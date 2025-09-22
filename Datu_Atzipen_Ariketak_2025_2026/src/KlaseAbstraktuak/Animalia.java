package KlaseAbstraktuak;

import java.io.Serializable;

public abstract class Animalia implements Serializable {
    private static final long serialVersionUID = 1L;

    private String izena;
    private String tamaina;

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getTamaina() {
        return tamaina;
    }

    public void setTamaina(String tamaina) {
        this.tamaina = tamaina;
    }

    public Animalia(String izena, String tamaina) {
        this.izena = izena;
        this.tamaina = tamaina;
    }

    @Override
    public String toString() {
        return "Animalia{" +
                "izena='" + izena + '\'' +
                ", tamaina='" + tamaina + '\'' +
                '}';
    }
}
