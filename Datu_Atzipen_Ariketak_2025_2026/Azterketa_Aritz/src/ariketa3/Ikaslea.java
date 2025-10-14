package ariketa3;

import java.io.Serializable;

//Serializable izan behar du fitxategi bitar batean gorde ahal izateko
public class Ikaslea implements Serializable {

	private String izena;
	private String modulua; // Adibidez: "Informatika", "Diseinua", "Kudeaketa"
	private double nota;

	public Ikaslea(String izena, String modulua, double nota) {
		this.izena = izena;
		this.modulua = modulua;
		this.nota = nota;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getModulua() {
		return modulua;
	}

	public void setModulua(String modulua) {
		this.modulua = modulua;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		return "Ikaslea{" + "izena='" + izena + '\'' + ", modulua='" + modulua + '\'' + ", nota=" + nota + '}';
	}
}