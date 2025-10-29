package ErrepasoAriketak.a05_Jokalariak.model;

public class Aurrelaria extends Jokalaria {


    public Aurrelaria(String izena, double altuera, int zenbakia) {
        super(izena, altuera, zenbakia);
    }

    @Override
    public void jokatu() {
        System.out.println("Gola sartu du...");
    }
}
