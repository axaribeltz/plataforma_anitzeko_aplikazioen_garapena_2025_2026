package ErrepasoAriketak.a05.model;

public class Aurrelaria extends Jokalaria {

    public Aurrelaria(String izena, double altuera, int zenbakia) {
        super(izena, altuera, zenbakia);
    }

    @Override
    public void jokatu() {
        System.out.println("Gola sartu du...");
    }
}
