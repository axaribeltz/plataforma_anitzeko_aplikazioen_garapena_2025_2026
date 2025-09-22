package KlaseAbstraktuak;

public class HezurGabeak extends Animalia {
    private int pixua;

    public HezurGabeak(String izena, String tamaina, int pixua) {
        super(izena, tamaina);
        this.pixua = pixua;
    }

    public int getPixua() {
        return pixua;
    }

    public void setPixua(int pixua) {
        this.pixua = pixua;
    }

    @Override
    public String toString() {
        return "HezurGabeak{" +
                "pixua=" + pixua +
                '}';
    }
}
