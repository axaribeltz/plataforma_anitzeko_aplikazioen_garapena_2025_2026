package FitxategiBitarrak.Objektuak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class IrakurriObjektua {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Fitxategi Mota Batzuen Adibidea\\ObjektuBitarrakAdibidea\\perOb.bit"));
		Pertsona pKargatua=(Pertsona) ois.readObject();
		
		System.out.println(pKargatua);
	}

}
