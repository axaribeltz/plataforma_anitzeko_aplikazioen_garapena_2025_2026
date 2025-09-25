package RandomAccessFitxategiak;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AusazkoFitxategiak {
	
	public static void main(String[] args) {
        // --- 1. Fitxategia ireki eta itxi ---
        try {
            // Fitxategi bat ireki irakurketa eta idazketa moduan
            RandomAccessFile raf = new RandomAccessFile("datuak.dat", "rw");
            System.out.println("Fitxategia ireki da irakurketa/idazketa moduan.");
            raf.close(); // Fitxategia beti itxi bukatzean
        } catch (IOException e) {
            System.err.println("Errorea fitxategira sartzean: " + e.getMessage());
        }

        
        // --- 2. Punteroaren posizioa kudeatu ---
		// Gogoratu fitxategiaren ibilbidea jartzea
        try (RandomAccessFile raf = new RandomAccessFile("", "rw")) {
            // Punteroaren hasierako posizioa
            System.out.println("\nPunteroaren hasierako posizioa: " + raf.getFilePointer()); // 0
            raf.seek(26);
            raf.writeBytes("\n");
            raf.seek(50);
            
            // 10 byte idatzi
            raf.writeBytes("Zer moduz!\n");
            System.out.println("Idatzi ondorengo posizioa: " + raf.getFilePointer()); // 12 (12 byte idatzi direlako)

            // Punteroa hasierara mugitu
            raf.seek(0);
            System.out.println("'seek' ondorengo posizioa: " + raf.getFilePointer()); // 0
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        
        // --- 3. Datu mota primitiboak idatzi eta irakurri ---
        try (RandomAccessFile raf = new RandomAccessFile("", "rw")) {
            // Langile baten datuak idatzi
            int id = 101;
            
            String izena = "Ana Lopez";
            double soldata = 4500.50;
            raf.writeInt(id);      // 4 byte idatzi
            raf.writeUTF(izena);   // String-a formatu berezi batean idatzi
            raf.writeDouble(soldata);  // 8 byte idatzi

            // Orain, langilearen datuak irakurri.
            // Lehenik, punteroa erregistroaren hasierara mugitu
            raf.seek(0);
            
            // Datuak irakurri
            int irakurritakoId = raf.readInt();
            System.out.println(raf.getFilePointer());
            String irakurritakoIzena = raf.readUTF();
            System.out.println(raf.getFilePointer());
            double irakurritakoSoldata = raf.readDouble();
            System.out.println(raf.getFilePointer());
            
            System.out.println("\nLangilearen datuak:");
            System.out.println("ID: " + irakurritakoId);
            System.out.println("Izena: " + irakurritakoIzena);
            System.out.println("Soldata: " + irakurritakoSoldata);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	    
	
}
