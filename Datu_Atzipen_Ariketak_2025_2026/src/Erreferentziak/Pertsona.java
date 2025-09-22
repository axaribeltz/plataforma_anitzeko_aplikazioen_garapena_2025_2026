package Erreferentziak;

import java.io.Serializable;

public class Pertsona implements Serializable {

    String izena;
    String abizena;
    int urtea;

    public Pertsona (String izena, String abizena, int urtea)
    {
        this.izena = izena;
        this.abizena = abizena;
        this.urtea = urtea;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena() {
        return abizena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    public int getUrtea() {
        return urtea;
    }

    public void setUrtea(int urtea) {
        this.urtea = urtea;
    }

    @Override
    public String toString() {
        return "Pertsona{" +
                "izena='" + izena + '\'' +
                ", abizena='" + abizena + '\'' +
                ", urtea=" + urtea +
                '}';
    }
}
