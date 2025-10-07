package ErrepasoAriketak.a04;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RandomAccesFilePringadillo {

    // Array con los nombres de las columnas de la base de datos
    static String[] zutabeak = {"ida", "izena", "dni"};

    // Array con el ancho (número de caracteres) de cada campo
    static int[] zabalerak = {2, 10, 9};

    // Variable que almacenará la longitud total de un registro (suma de todos los anchos)
    static int longReg = 0;

    // Ruta del archivo donde se guardarán los registros
    static String fitxIzena = "/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a04/randomAdibidea.dat";

    public static void main(String[] args) {
        // Calcula la longitud total de un registro sumando todos los anchos
        kalkulatuLongReg();
        System.out.println("Erregistiro zabalera: " + longReg);

        // Arrays con datos de ejemplo para insertar
        int[] idak = {1, 2};
        String[] izenak = {"pepe", "jose"};
        String[] dniak = {"12345678P", "87654321Q"};

        // Array temporal para almacenar un registro completo
        String[] erregistroa = new String[3];

       // Bloque comentado: inserta todos los registros en el archivo
       for (int i=0; i < idak.length; i++) {
          erregistroa[0] = Integer.toString(idak[i]); // Convierte el ID a String
          erregistroa[1] = izenak[i];                 // Asigna el nombre
          erregistroa[2] = dniak[i];                  // Asigna el DNI
          txertatu(erregistroa);                      // Inserta el registro en el archivo
       }

        // Obtiene y muestra el nombre del registro número 1
        getErregIzena(1);

        System.out.println("Orain bilatu erregistroa:");

        // Busca un registro por nombre
        bilatuErregistroaIzenarekiko("pepe");
    }

    // Método que calcula la longitud total de un registro
    public static void kalkulatuLongReg() {
        // Suma todos los anchos de los campos
        for (int i=0; i<zabalerak.length; i++) {
            longReg+=zabalerak[i];
        }
    }

    // Método para insertar un registro al final del archivo
    public static void txertatu(String[] erregistroa) {
        // Try-with-resources: abre el archivo en modo lectura/escritura y lo cierra automáticamente
        try(RandomAccessFile raf = new RandomAccessFile(fitxIzena, "rw")) {

            // Posiciona el puntero al final del archivo
            raf.seek(raf.length());

            // Variable para almacenar cada campo formateado
            String valorCampoForm = "";

            // Formatea y escribe el campo ID (2 caracteres, rellena con espacios a la derecha)
            valorCampoForm = String.format("%1$-" + zabalerak[0] + "s",  erregistroa[0]);
            raf.write(valorCampoForm.getBytes("UTF-8"), 0, zabalerak[0]);

            // Formatea y escribe el campo NOMBRE (10 caracteres)
            valorCampoForm = String.format("%1$-" + zabalerak[1] + "s",  erregistroa[1]);
            raf.write(valorCampoForm.getBytes("UTF-8"), 0, zabalerak[1]);

            // Formatea y escribe el campo DNI (9 caracteres)
            valorCampoForm = String.format("%1$-" + zabalerak[2] + "s",  erregistroa[2]);
            raf.write(valorCampoForm.getBytes("UTF-8"), 0, zabalerak[2]);

        } catch (Exception e) {
            // Captura y muestra cualquier error
            System.out.println(e.getMessage());
        }
    }

    // Método que obtiene y muestra el nombre de un registro específico
    public static void getErregIzena(int erregZkia) {
        try(RandomAccessFile raf = new RandomAccessFile(fitxIzena, "rw")) {

            // Calcula la posición del campo "nombre" en el registro solicitado
            // (erregZkia-1) * longReg = inicio del registro
            // + zabalerak[0] = salta el campo ID para llegar al nombre
            raf.seek((long) (erregZkia - 1) * longReg + zabalerak[0]);

            // Crea un array de bytes del tamaño del campo nombre
            byte[] izena = new byte[zabalerak[1]];

            // Lee el nombre desde el archivo
            raf.read(izena);

            // Convierte los bytes a String y lo muestra
            System.out.println(new String(izena, StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Método que busca un registro por nombre
    public static void bilatuErregistroaIzenarekiko(String bilaketaIzena) {
        try(RandomAccessFile raf = new RandomAccessFile(fitxIzena, "rw")) {

            // Bandera para controlar si se encontró el registro
            boolean aurkitua = false;

            // Contador del número de registro actual
            int erregZenbakia = 1;

            // Array para almacenar el nombre leído
            byte[] irakurritakoIzena = new byte[zabalerak[1]];

            // Bucle que recorre todos los registros hasta encontrar coincidencia
            // o hasta llegar al final del archivo
            while (!aurkitua && (erregZenbakia <= raf.length()/longReg)) {

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