package Erreferentziak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class IrakurriObjektua {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\Erreferentziak\\perOb.bit"));
        Pertsona pKargatu = (Pertsona) input.readObject();

        System.out.println(pKargatu);

    }
}
