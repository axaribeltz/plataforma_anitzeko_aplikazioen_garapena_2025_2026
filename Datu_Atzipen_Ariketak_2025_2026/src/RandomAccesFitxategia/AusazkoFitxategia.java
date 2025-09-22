package RandomAccesFitxategia;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AusazkoFitxategia {
    public static void main(String[] args) {

        //TEXTU PLANOA IDATZI // "rw" irakurri/idatzi
        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\RandomAccesFitxategia\\Artikuloa.dat","rw")) {

            //PUNTEROAREN LEHENENGO POSIZIOA
            System.out.println("\nPunteroaren hasierako posizioa:" + raf.getFilePointer());

            // 10 byte idatzi
            raf.writeBytes("Zer moduz zare?");
            System.out.println("Idatzi ondorengo posizioa: " + raf.getFilePointer());

            // Punteroa mugitu posizioz, posizio bakoitza letra, zenbaki, espazio dira...
            raf.seek(10);
            System.out.println("'seek' ondorengo posizioa: " + raf.getFilePointer());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\2AM3-4\\Documents\\datu-atzipena\\src\\RandomAccesFitxategia\\LangileBatenDatuak.dat","rw")) {
            //Langile baten datuak idatzi
            int id = 101;
            String izena = "Patxi Lasa";
            double soldata = 1500.2;

            raf.writeInt(id); //4 byte idatzi
            raf.writeUTF(izena); //String-a formatu berezi batean idatzi
            raf.writeDouble(soldata); //7 bytes idatzi

            //Orain langilearen datuak irakurri
            //Lehenengo, punteroa erregistroaren hasierara mugitu
            raf.seek(0);

            int irakurritakoId = raf.readInt();
            String irakurritakoIzena = raf.readUTF();
            double irakurritakoSoldata = raf.readDouble();

            System.out.println("\nLangilearen datuak: ");
            System.out.println("ID: " + irakurritakoId);
            System.out.println("Izena: " + irakurritakoIzena);
            System.out.println("Soldata: " + irakurritakoSoldata);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
