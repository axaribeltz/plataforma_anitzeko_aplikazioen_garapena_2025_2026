package repaso_ariketa.Modeloa;

import jdk.jfr.Timestamp;

public class ErabiltzaileMugimenduak {
    static Timestamp timestamp;
    String izena;
    int kantitatea;

    public ErabiltzaileMugimenduak(String izena, int kantitatea) {
        this.izena = izena;
        this.kantitatea = kantitatea;
    }

    public static Timestamp getTimestamp() {
        return timestamp;
    }

    public static void setTimestamp(Timestamp timestamp) {
        repaso_ariketa.Modeloa.ErabiltzaileMugimenduak.timestamp = timestamp;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public int getKantitatea() {
        return kantitatea;
    }

    public void setKantitatea(int kantitatea) {
        this.kantitatea = kantitatea;
    }

    @Override
    public String toString() {
        return "ErabiltzaileMugimenduak{" +
                "izena='" + izena + '\'' +
                ", kantitatea=" + kantitatea +
                '}';
    }
}

