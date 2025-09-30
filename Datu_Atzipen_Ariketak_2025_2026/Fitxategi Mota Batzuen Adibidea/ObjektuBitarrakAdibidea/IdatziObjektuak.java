package FitxategiBitarrak.Objektuak;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class IdatziObjektuak {

	public static void main(String[] args) throws IOException {
		Pertsona p1= new Pertsona("AAAA", "AAAA", "123456");
		Pertsona p2= new Pertsona("BBBB", "BBBB", "123456");
		Pertsona p3= new Pertsona("CCCC", "CCCC", "123456");
		
		
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\aelorza\\eclipse-workspace\\Fitxategiak\\src\\FitxategiBitarrak\\Objektuak\\perOb.bit"));
		
		oos.writeObject(p1);		
		oos.close();
		
		
//		for (int i = 0; i < 3; i++) {
//			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("/src/FitxategiBitarrak/Objektuak/"));
//		}
		
	}

}
