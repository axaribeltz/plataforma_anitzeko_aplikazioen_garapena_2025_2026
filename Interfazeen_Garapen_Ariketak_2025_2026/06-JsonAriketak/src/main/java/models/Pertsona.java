/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 *
 * @author 2AM3-4
 */
public class Pertsona {

    private String izena;
    private String abizena;
    private int adina;

    public Pertsona(String izena, String abizena, int adina) {
        this.izena = izena;
        this.abizena = abizena;
        this.adina = adina;
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

    @Override
    public String toString() {
        return "Pertsona{" + "izena=" + izena + ", abizena=" + abizena + ", adina=" + adina + '}';
    }


}
