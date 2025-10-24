package KlaseAbstraktuak;

import Erreferentziak.Pertsona;

import java.io.*;
import java.util.ArrayList;

public class AnimaliaApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException {
        Hezurdunak h1 = new Hezurdunak("Ardia", "20", 100);
        Hezurdunak h2 = new Hezurdunak("Txakurra", "20", 100);
        HezurGabeak h3 = new HezurGabeak("Barea", "20", 100);

        ArrayList<Animalia> animaliaList = new ArrayList<Animalia>();
        animaliaList.add(h1);
        animaliaList.add(h2);
        animaliaList.add(h3);

        //GORDE FITXATEGIAN
        ObjectOutputStream output  = new ObjectOutputStream(new FileOutputStream("C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\KlaseAbstraktuak\\animaliaOb.bit"));
        output .writeObject(animaliaList);
        output .flush();
        output .close();

        //IRAKURRI FITXATEGIA
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\KlaseAbstraktuak\\animaliaOb.bit"));

        ArrayList<Animalia> animaliLista = (ArrayList<Animalia>) input.readObject();
        input.close();

        int hezurrakDitu=0, ezDituHezurrak=0;
        for (Animalia a : animaliLista) {
            if (a instanceof Hezurdunak) {
                hezurrakDitu++;
            } else if (a instanceof HezurGabeak) {
                ezDituHezurrak++;
            }
        }
        System.out.println("Hezurrak dituzten animaliak: " + hezurrakDitu);
        System.out.println("Hezurrak ez dituzten animaliak: " + ezDituHezurrak);
    }
}
