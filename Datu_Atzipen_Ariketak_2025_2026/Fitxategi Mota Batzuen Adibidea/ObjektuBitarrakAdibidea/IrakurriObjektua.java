package FitxategiBitarrak.Objektuak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class IrakurriObjektua {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\aelorza\\eclipse-workspace\\Fitxategiak\\src\\FitxategiBitarrak\\Objektuak\\perOb.bit"));
		Pertsona pKargatua=(Pertsona) ois.readObject();
		
		System.out.println(pKargatua);
	}

}
