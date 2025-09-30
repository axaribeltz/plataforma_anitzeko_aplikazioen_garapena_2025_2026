package RandomAccessFitxategiak;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AriketaAuzFitx {
	 public static void main(String[] args) {
		 Scanner irakurgailua = new Scanner(System.in);
	     int aukera = 0;
	     
	     do {
	         menuaErakutsi();
	         try {
	             aukera = Integer.parseInt(irakurgailua.nextLine());
	             switch (aukera) {
	                 case 1:
	                     pertsonaGorde(irakurgailua);
	                     break;
	                 case 2:
	                     pertsonakIkusi();
	                     break;
	                 case 3:
	                     System.out.println("Programatik irteten. Ikusi arte!");
	                     break;
	                 default:
	                     System.out.println("Aukera okerra. Mesedez, saiatu berriro.");
	             }
	         } catch (NumberFormatException e) {
	             System.out.println("Sarrera okerra. Mesedez, sartu zenbaki bat.");
	         }
	     } while (aukera != 3);

	     irakurgailua.close();
	 }

	 private static void menuaErakutsi() {
	     System.out.println("\n--- Pertsonen Kudeaketa Menua ---");
	     System.out.println("1. Pertsona bat gorde");
	     System.out.println("2. Pertsona guztiak ikusi");
	     System.out.println("3. Irten");
	     System.out.print("Aukeratu bat: ");
	 }

	 private static void pertsonaGorde(Scanner irakurgailua) {
	     try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\aelorza\\eclipse-workspace\\Fitxategiak\\src\\RandomAccessFitxategiak\\pertsona.dat", "rw")) {
	         System.out.println("\n--- Pertsona Berria Gorde ---");
	         
	         // Punteroa fitxategiaren amaierara mugitu, erregistro berria gehitzeko
	         raf.seek(raf.length());
	         
	         System.out.print("Sartu ID-a: ");
	         int id = Integer.parseInt(irakurgailua.nextLine());
	         
	         System.out.print("Sartu izena: ");
	         String izena = irakurgailua.nextLine();
	         
	         System.out.print("Sartu soldata: ");
	         double soldata = Double.parseDouble(irakurgailua.nextLine());
	         
	         // Datuak fitxategian idatzi
	         raf.writeInt(id);
	         raf.writeUTF(izena);
	         raf.writeDouble(soldata);
	         
	         System.out.println("Pertsona ondo gorde da.");
	         
	     } catch (IOException | NumberFormatException e) {
	    	 
	         System.out.println("Errorea pertsona gordetzean: " + e.getMessage());
	         
	     }
	 }

	 private static void pertsonakIkusi() {
	     try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\aelorza\\eclipse-workspace\\Fitxategiak\\src\\RandomAccessFitxategiak\\pertsona.dat", "r")) {
	         System.out.println("\n--- Pertsona Zerrenda ---");
	         
	         if (raf.length() == 0) {
	             System.out.println("Ez dago pertsonarik erregistratuta.");
	             return;
	         }
	         
	         // Punteroa fitxategiaren hasierara mugitu, hasieratik irakurtzeko
	         raf.seek(0);
	         
	         while (raf.getFilePointer() < raf.length()) {
	             int id = raf.readInt();
	             String izena = raf.readUTF();
	             double soldata = raf.readDouble();
	             
	             System.out.println("-------------------------");
	             System.out.println("ID-a: " + id);
	             System.out.println("Izena: " + izena);
	             System.out.println("Soldata: " + soldata);
	         }
	         
	     } catch (IOException e) {
	         System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
	     }
	}
}
