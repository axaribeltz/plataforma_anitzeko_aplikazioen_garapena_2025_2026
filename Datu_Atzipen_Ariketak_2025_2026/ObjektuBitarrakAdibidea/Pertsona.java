package FitxategiBitarrak.Objektuak;

import java.io.Serializable;

public class Pertsona implements Serializable{
	private String Izena;
	private String Abizena;
	private String Telefonoa;
	public Pertsona(String izena, String abizena, String telefonoa) {
		super();
		Izena = izena;
		Abizena = abizena;
		Telefonoa = telefonoa;
	}
	public String getIzena() {
		return Izena;
	}
	public void setIzena(String izena) {
		Izena = izena;
	}
	public String getAbizena() {
		return Abizena;
	}
	public void setAbizena(String abizena) {
		Abizena = abizena;
	}
	public String getTelefonoa() {
		return Telefonoa;
	}
	public void setTelefonoa(String telefonoa) {
		Telefonoa = telefonoa;
	}
	@Override
	public String toString() {
		return "Pertsona [Izena=" + Izena + ", Abizena=" + Abizena + ", Telefonoa=" + Telefonoa + "]";
	}
	
}
