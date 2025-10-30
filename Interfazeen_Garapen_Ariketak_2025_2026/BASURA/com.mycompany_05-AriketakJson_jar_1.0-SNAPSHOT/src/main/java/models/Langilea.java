/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author 2AM3-4
 */
public class Langilea {

    private int id;
    private String izena;
    private String lantegia;

    public Langilea(int id, String izena, String lantegia) {
        this.id = id;
        this.izena = izena;
        this.lantegia = lantegia;
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

    public void setIzena (String izena) {
        this.izena = izena;
    }

    public String getLantegia() {
        return lantegia;
    }

    public void setLantegia(String lantegia) {
        this.lantegia = lantegia;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + izena + ", lantegia=" + lantegia + '}';
    }
}
