/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloa;

import java.time.LocalDate;

/**
 *
 * @author 2AM3-4
 */
public class Bezeroak {
    int id;
    String izena;
    String hiria;
    String sexua;
    String mugikorra;
    LocalDate jaiotzeData;

    public Bezeroak(int id, String izena, String hiria, String sexua, String mugikorra, LocalDate jaiotzeData) {
        this.id = id;
        this.izena = izena;
        this.hiria = hiria;
        this.sexua = sexua;
        this.mugikorra = mugikorra;
        this.jaiotzeData = jaiotzeData;
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

    public String getHiria() {
        return hiria;
    }

    public void setHiria(String hiria) {
        this.hiria = hiria;
    }

    public String getSexua() {
        return sexua;
    }

    public void setSexua(String sexua) {
        this.sexua = sexua;
    }

    public String getMugikorra() {
        return mugikorra;
    }

    public void setMugikorra(String mugikorra) {
        this.mugikorra = mugikorra;
    }

    public LocalDate getJaiotzeData() {
        return jaiotzeData;
    }

    public void setJaiotzeData(LocalDate jaiotzeData) {
        this.jaiotzeData = jaiotzeData;
    }

    @Override
    public String toString() {
        return "Bezeroak{" + "id=" + id + ", izena=" + izena + ", hiria=" + hiria + ", sexua=" + sexua + ", mugikorra=" + mugikorra + ", jaiotzeData=" + jaiotzeData + '}';
    }
}
