package Pertsona;
import java.io.*;

public class NirePertsona {
    public static void main(String[] args) throws IOException {
        Pertsona p1 = new Pertsona("Aritz","Ibañez",24);

        ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\Pertsona\\perOb.bit"));

        oss.writeObject(p1);
        oss.flush();
        oss.close();
    }
}
