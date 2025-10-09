package ErrepasoAriketak.a07_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ErrepasoAriketa_7 {
    static void main(String[] args) {
        String fitxategiaTextua = "/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a07_RandomAccessFile/textua.txt";
        String fitxategiaLangile = "/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a07_RandomAccessFile/textuaBi.dat";

        // 1️⃣ Idatzi testua
        idatziTestua(fitxategiaTextua);

        // 2️⃣ Irakurri testua
        irakurriTestua(fitxategiaTextua);

        // 3️⃣ Bilatu "moduz" testuan
        bilatuHitzaTestuan(fitxategiaTextua, "tyy");

        // 4️⃣ Idatzi langilea fitxategi binario batean
        idatziLangilea(fitxategiaLangile);

        // 5️⃣ Irakurri langilea fitxategi binarioatik
        irakurriLangilea(fitxategiaLangile);
    }

    /** Idatzi testua fitxategi plano batean */
    public static void idatziTestua(String fitxategia) {
        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {
            // Mugitu punteroa bukaerara
            raf.seek(raf.length());
            raf.writeBytes(" Zer moduz zare?");
            System.out.println("✅ Testua idatzi da: " + raf.getFilePointer());
        } catch (IOException e) {
            System.out.println("❌ Errorea idaztean: " + e.getMessage());
        }
    }

    /** Irakurri testua fitxategi plano batetik */
    public static void irakurriTestua(String fitxategia) {
        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {
            raf.seek(0);
            System.out.println("------ Fitxategiko testua ------");
            String linea;
            while ((linea = raf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("❌ Errorea irakurtzean: " + e.getMessage());
        }
    }

    /** Bilatu hitz zehatz bat fitxategi plano batean */
    public static void bilatuHitzaTestuan(String fitxategia, String hitza) {
        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {
            long puntero = 0;
            String linea;
            while ((linea = raf.readLine()) != null) {
                int index = linea.indexOf(hitza);
                if (index != -1) {
                    long posicion = puntero + index;
                    System.out.println("✅ '" + hitza + "' aurkitu da posizioan: " + posicion);
                    break;
                } else {
                    System.out.println("❌ EZ da '" + hitza + "' aurkitu");
                }
                puntero = raf.getFilePointer();
            }
        } catch (IOException e) {
            System.out.println("❌ Errorea bilatzean: " + e.getMessage());
        }
    }

    /** Idatzi langilea fitxategi binario batean */
    public static void idatziLangilea(String fitxategia) {
        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "rw")) {
            int id = 101;
            String izena = "Patxi Lasa";
            String izena_2 = "Aritz Ibañez";
            double soldata = 1500.2;

            raf.writeInt(id);
            raf.writeUTF(izena);
            raf.writeUTF(izena_2);
            raf.writeDouble(soldata);

            System.out.println("✅ Langilea idatzi da fitxategian.");
        } catch (IOException e) {
            System.out.println("❌ Errorea langilea idaztean: " + e.getMessage());
        }
    }

    /** Irakurri langilea fitxategi binario batetik */
    public static void irakurriLangilea(String fitxategia) {
        try (RandomAccessFile raf = new RandomAccessFile(fitxategia, "r")) {
            raf.seek(0);
            int id = raf.readInt();
            String izena1 = raf.readUTF();
            String izena2 = raf.readUTF();
            double soldata = raf.readDouble();

            System.out.println("\n------ Langilearen datuak ------");
            System.out.println("ID: " + id);
            System.out.println("Izena 1: " + izena1);
            System.out.println("Izena 2: " + izena2);
            System.out.println("Soldata: " + soldata);
        } catch (IOException e) {
            System.out.println("❌ Errorea langilea irakurtzean: " + e.getMessage());
        }
    }
}
