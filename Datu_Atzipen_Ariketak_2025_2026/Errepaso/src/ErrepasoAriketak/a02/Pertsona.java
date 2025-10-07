package ErrepasoAriketak.a02;

import java.io.Serializable;

public class Pertsona implements Serializable {
    private String Izena;
    private String Abizena;
    private Double Altuera;

    public Pertsona(String izena, String abizena, Double altuera) {
        super();
        Izena = izena;
        Abizena = abizena;
        Altuera = altuera;
    }

    public String getIzena() {
        return Izena;
    }

    public String getAbizena() {
        return Abizena;
    }

    public Double getAltuera() {
        return Altuera;
    }

    public void setIzena(String izena) {
        Izena = izena;
    }

    public void setAbizena(String abizena) {
        Abizena = abizena;
    }

    public void setAltuera(Double altuera) {
        Altuera = altuera;
    }

    @Override
    public String toString() {
        return " Izena, Abizena, Altuera ";
    }
}
