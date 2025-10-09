package ErrepasoAriketak.a05_JokalariakRefactor.model;

public class Atezaina extends Jokalaria {

    public Atezaina(String izena, double altuera, int zenbakia) {
        super(izena, altuera, zenbakia);
    }

    @Override
    public void jokatu() {
        System.out.println(getIzena() + " baloia gelditu...");
    }

}
