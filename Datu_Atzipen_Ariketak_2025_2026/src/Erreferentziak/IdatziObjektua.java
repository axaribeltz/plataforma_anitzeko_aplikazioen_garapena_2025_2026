package Erreferentziak;
import java.io.*;

public class IdatziObjektua {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Pertsona p1 = new Pertsona("Aritz","Ibañez",24);

        ObjectOutputStream output  = new ObjectOutputStream(new FileOutputStream("C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\Erreferentziak\\perOb.bit"));

        output .writeObject(p1);
        output .flush();
        output .close();
    }
}
