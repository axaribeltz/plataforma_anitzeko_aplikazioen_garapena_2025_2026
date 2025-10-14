package ariketa3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class DatuAnalista {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Ikaslea> ikasleZerrenda = new ArrayList<>();
        //arrayListOsoaIkusi();
        irakurriIkasleKopuruOsoa();
        irakurriInformatikaModulokoIkasleenBatazBestekoNota();
        idatziNotarikBaxuena();
    }

    public static void irakurriIkasleKopuruOsoa()  {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\ariketa3\\ikaslea.dat"))) {

            ArrayList<Ikaslea> ikasleZerrenda = (ArrayList<Ikaslea>) input.readObject();
            System.out.println("IKASLE KOPURU: " + ikasleZerrenda.size());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    public static void arrayListOsoaIkusi() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\ariketa3\\ikaslea.dat"))) {

            ArrayList<Ikaslea> ikasleZerrenda = (ArrayList<Ikaslea>) input.readObject();
            //ArrayList Osoa
            for (Ikaslea ika : ikasleZerrenda) {
                System.out.println("ARRAY LIST OSOA:");
                System.out.println(ika);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    public static void irakurriInformatikaModulokoIkasleenBatazBestekoNota() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\ariketa3\\ikaslea.dat"))) {

            ArrayList<Ikaslea> ikasleZerrenda = (ArrayList<Ikaslea>) input.readObject();
            ArrayList<Ikaslea> informatikakoIkasleak = new ArrayList<>();

            for (int i = 0; i < ikasleZerrenda.size(); i++) {
                Ikaslea ika = ikasleZerrenda.get(i);
                if (Objects.equals(ika.getModulua(), "Informatika")){
                    informatikakoIkasleak.add(ika);
                }
            }

            double suma = 0;
            double promedio;

            for (int i = 0; i < informatikakoIkasleak.size(); i++) {
                Ikaslea ika = informatikakoIkasleak.get(i);
                suma += ika.getNota();
            }

            promedio = suma / informatikakoIkasleak.size();
            System.out.println("El promedio de las notas es: " + promedio);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    public static void idatziNotarikBaxuena() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Azterketa_Aritz\\src\\ariketa3\\ikaslea.dat"))) {

            ArrayList<Ikaslea> ikasleZerrenda = (ArrayList<Ikaslea>) input.readObject();
            ArrayList<Double> ikasleNota = new ArrayList<>();
            for (int i = 0; i < ikasleZerrenda.size(); i++) {
                Ikaslea ika = ikasleZerrenda.get(i);
                ikasleNota.add(ika.getNota());
            }
            double notaTxikiena = Integer.MAX_VALUE;
            for (double nota : ikasleNota) {
                if (nota < notaTxikiena) {
                    notaTxikiena = nota;
                }
            }
            System.out.println("Nota txikiena da: " + notaTxikiena);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }
}
