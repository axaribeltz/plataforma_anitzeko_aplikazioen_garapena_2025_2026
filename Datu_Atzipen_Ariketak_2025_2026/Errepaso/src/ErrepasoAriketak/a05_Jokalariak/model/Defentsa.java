package ErrepasoAriketak.a05_Jokalariak.model;

public class Defentsa extends Jokalaria{

    public Defentsa(String izena, double altuera, int zenbakia) {
        super(izena, altuera, zenbakia);
    }

    @Override
    public void jokatu() {
        System.out.println("Baloia moztu du...");
    }
}
