package ErrepasoAriketak.a05_JokalariakRefactor.model;

public class Defentsa extends Jokalaria {

    public Defentsa(String izena, double altuera, int zenbakia) {
        super(izena, altuera, zenbakia);
    }

    @Override
    public void jokatu() {
        System.out.println(getIzena() + " baloia moztu du...");
    }
}
