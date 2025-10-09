package ErrepasoAriketak.a07_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class ErrepasoAriketa_7 {
    // Variable que almacenará la longitud total de un registro (suma de todos los anchos)
    static int longReg = 0;

    // Array con el ancho (número de caracteres) de cada campo
    static int[] zabalerak = {2, 10, 9};

    static void main(String[] args) {
        //Textu plano batean idatzi, "rw" irakurri/idatzi
        try (RandomAccessFile raf = new RandomAccessFile("/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a07_RandomAccessFile/textua.txt","rw")) {

            //Punteroaren hasierako posizioa
            System.out.println("\nPunteroaren hasierako posizioa:" + raf.getFilePointer());

            // Punteroa mugitu azkeneko posiziora, posizio bakoitza letra, zenbaki, espazio dira...
            raf.seek(raf.length());
            System.out.println("'seek' ondorengo posizioa: " + raf.getFilePointer());

            // 10 byte idatzi
            raf.writeBytes(" Zer moduz zare?");
            System.out.println("Idatzi ondorengo posizioa: " + raf.getFilePointer());

            // Mover puntero al inicio para leer
            raf.seek(0);

            System.out.println("------ Fitxategiko testua ------");
            String line;
            while ((line = raf.readLine()) != null) {
                System.out.println(line);
            }

            // Posiciona el puntero justo donde empieza "moduz"
            raf.seek(4);

            byte[] buffer = new byte[6]; // "moduz" tiene 6 letras
            raf.readFully(buffer);       // lee exactamente 6 bytes
            String moduz = new String(buffer);

            System.out.println("Palabra leída: " + moduz);

        } catch (IOException e) {
            System.out.println("❌ Errorea fitxategiak bateratzean: " + e.getMessage());
        }

        // Busca un registro por nombre
        bilatuErregistroaIzenarekiko("moduz");

        try (RandomAccessFile raf = new RandomAccessFile("/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a07_RandomAccessFile/textuaBi.dat","rw")) {
            //Langile baten datuak idatzi
            int id = 101;
            String izena = "Patxi Lasa";
            String izena_2 = "Aritz Ibañez";
            double soldata = 1500.2;

            raf.writeInt(id); //4 byte idatzi
            raf.writeUTF(izena); //String-a formatu berezi batean idatzi
            raf.writeUTF(izena_2);
            raf.writeDouble(soldata); //7 bytes idatzi

            //Orain langilearen datuak irakurri
            //Lehenengo, punteroa erregistroaren hasierara mugitu
            raf.seek(0);

            int irakurritakoId = raf.readInt();
            String irakurritakoIzena = raf.readUTF();
            String irakurritakoIzena2 = raf.readUTF();
            double irakurritakoSoldata = raf.readDouble();

            System.out.println("\nLangilearen datuak: ");
            System.out.println("ID: " + irakurritakoId);
            System.out.println("Izena: " + irakurritakoIzena);
            System.out.println("Izena 2: " + irakurritakoIzena2);
            System.out.println("Soldata: " + irakurritakoSoldata);

        } catch (IOException e) {
            System.out.println("❌ Errorea fitxategiak bateratzean: " + e.getMessage());
        }
    }

    // Método que busca un registro por nombre
    public static void bilatuErregistroaIzenarekiko(String bilaketaIzena) {
        try (RandomAccessFile raf = new RandomAccessFile("/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a07_RandomAccessFile/textuaBi.dat", "rw")) {

            // Bandera para controlar si se encontró el registro
            boolean aurkitua = false;
            // Contador del número de registro actual
            int erregZenbakia = 1;
            // Array para almacenar el nombre leído
            byte[] irakurritakoIzena = new byte[zabalerak[1]];
            // Bucle que recorre todos los registros hasta encontrar coincidencia
            // o hasta llegar al final del archivo
            while (!aurkitua && (erregZenbakia <= raf.length() / longReg)) {
                // Posiciona el puntero en el campo "nombre" del registro actual
                raf.seek((long) (erregZenbakia - 1) * longReg + zabalerak[0]);

                // Lee el nombre del registro actual
                raf.read(irakurritakoIzena);

                // Compara el nombre buscado con el nombre leído (ignorando mayúsculas y espacios)
                if (bilaketaIzena.toLowerCase().equals(new String(irakurritakoIzena, StandardCharsets.UTF_8).trim().toLowerCase())) {
                    aurkitua = true; // Marca como encontrado
                    System.out.println(bilaketaIzena + " izena, " + erregZenbakia + " erregistroan dago");
                }

                // Pasa al siguiente registro
                erregZenbakia++;
            }

            // Si no se encontró, muestra mensaje
            if (!aurkitua) {
                System.out.println("Ez da izen hori fitxategian aurkitu");
            }

        } catch (Exception e) {
            // Muestra errores en el stream de error estándar
            System.err.println(e.getMessage());
        }
    }
}