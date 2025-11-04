package apunteak_access;
import java.util.ArrayList;

import apunteak_access.DBKontaktua;

public class ProgramaNagusia {
	public static void main(String[] args) throws ClassNotFoundException {
		DBKontaktua dbKontaktua=new DBKontaktua();
		ArrayList<Kontaktua> kontaktuak=dbKontaktua.kontaktuGuztiakLortu();
		
		for (Kontaktua kontaktua : kontaktuak) {
			System.out.println(kontaktua);
		}
	}
}
