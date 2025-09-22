package KlaseAbstraktuak;

public class Hezurdunak extends Animalia {
    private int hezurKop;

    public Hezurdunak(String izena, String tamaina, int hezurKop) {
        super(izena, tamaina);
        hezurKop = hezurKop;
    }

    public int getHezurKop() {
        return hezurKop;
    }

    public void setHezurKop(int hezurKop) {
        hezurKop = hezurKop;
    }

    @Override
    public String toString() {
        return "Hezurdunak{" +
                "HezurKop=" + hezurKop +
                '}';
    }
}
