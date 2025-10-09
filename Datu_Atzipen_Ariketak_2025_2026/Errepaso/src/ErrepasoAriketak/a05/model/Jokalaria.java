package ErrepasoAriketak.a05.model;

import java.io.Serializable;

public abstract class Jokalaria implements Serializable {
    private String izena;
    private double altuera;
    private int zenbakia;

    public abstract void jokatu();
    public void mugitu() {
        System.out.println("Zelaian korrika ari naiz.");
    }

    public Jokalaria(String izena, double altuera, int zenbakia) {
        this.izena = izena;
        this.altuera = altuera;
        this.zenbakia = zenbakia;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public double getAltuera() {
        return altuera;
    }

    public void setAltuera(double altuera) {
        this.altuera = altuera;
    }

    public int getZenbakia() {
        return zenbakia;
    }

    public void setZenbakia(int zenbakia) {
        this.zenbakia = zenbakia;
    }

    @Override
    public String toString() {
        return "Jokalaria{" +
                "izena='" + izena + '\'' +
                ", altuera=" + altuera +
                ", zenbakia=" + zenbakia +
                '}';
    }
}
