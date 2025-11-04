import org.bson.Document;
import org.bson.types.ObjectId;

public class Erabiltzailea {
    private ObjectId id;
    private String izena;
    private int adina;
    private String hiria;

    // Eraikitzailea aplikaziotik erabiltzaile berri bat sortzeko
    public Erabiltzailea(String izena, int adina, String hiria) {
        this.id = new ObjectId(); // MongoDB-k ID bat sortuko du
        this.izena = izena;
        this.adina = adina;
        this.hiria = hiria;
    }


    // Gure Erabiltzailea objektua BSON Dokumentu bihurtzeko metodoa
    public Document dokumentuBihurtu() {
        return new Document("_id", this.id)
                .append("izena", this.izena)
                .append("adina", this.adina)
                .append("hiria", this.hiria);
    }
    
    // Getters eta Setters (laburtuta)
    public String getIzena() { return izena; }
    public void setAdina(int adina) { this.adina = adina; }

    @Override
    public String toString() {
        return "Erabiltzailea{" +
               "id=" + id +
               ", izena='" + izena + '\'' +
               ", adina=" + adina +
               ", hiria='" + hiria + '\'' +
               '}';
    }
}