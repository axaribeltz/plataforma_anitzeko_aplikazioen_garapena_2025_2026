/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 2AM3-4
 */
public class Empleado {

    private int id;
    private String nombre;
    private String lantegia;

    public Empleado(int id, String nombre, String lantegia) {
        this.id = id;
        this.nombre = nombre;
        this.lantegia = lantegia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLantegia() {
        return lantegia;
    }

    public void setLantegia(String lantegia) {
        this.lantegia = lantegia;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", lantegia=" + lantegia + '}';
    }
}
