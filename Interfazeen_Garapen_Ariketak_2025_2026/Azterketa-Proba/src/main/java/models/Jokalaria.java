/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Aritz
 */
public class Jokalaria {
    int id;
    String izena;
    String abizena;
    int adina;
    String hiria;
    int puntuak;
    int irabazita;
    int galduta;

    public Jokalaria(int id, String izena, String abizena, int adina, String hiria, int puntuak, int irabazita, int galduta) {
        this.id = id;
        this.izena = izena;
        this.abizena = abizena;
        this.adina = adina;
        this.hiria = hiria;
        this.puntuak = puntuak;
        this.irabazita = irabazita;
        this.galduta = galduta;
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

    public String getAbizena() {
        return abizena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    public int getAdina() {
        return adina;
    }

    public void setAdina(int adina) {
        this.adina = adina;
    }

    public String getHiria() {
        return hiria;
    }

    public void setHiria(String hiria) {
        this.hiria = hiria;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public void setPuntuak(int puntuak) {
        this.puntuak = puntuak;
    }

    public int getIrabazita() {
        return irabazita;
    }

    public void setIrabazita(int irabazita) {
        this.irabazita = irabazita;
    }

    public int getGalduta() {
        return galduta;
    }

    public void setGalduta(int galduta) {
        this.galduta = galduta;
    }

    @Override
    public String toString() {
        return "Jokalaria{" + "id=" + id + ", izena=" + izena + ", abizena=" + abizena + ", adina=" + adina + ", hiria=" + hiria + ", puntuak=" + puntuak + ", irabazita=" + irabazita + ", galduta=" + galduta + '}';
    }
}
