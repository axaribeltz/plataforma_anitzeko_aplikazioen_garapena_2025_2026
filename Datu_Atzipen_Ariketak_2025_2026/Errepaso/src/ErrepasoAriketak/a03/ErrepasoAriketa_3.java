package ErrepasoAriketak.a03;

import java.io.*; // Importa todas las clases de entrada/salida de Java

public class ErrepasoAriketa_3 {
    public static void main(String[] args) throws IOException { // Declara que el método puede lanzar excepciones de E/S

        // Define la ruta del archivo que vamos a leer
        String archivoOriginal = "/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a03/Idiazabal_bit.dat";

        // Define la ruta del archivo temporal donde escribiremos los cambios
        String archivoTemporal = "/home/axari/Documentos/plataforma_anitzeko_aplikazioen_garapena_2025_2026/Datu_Atzipen_Ariketak_2025_2026/Errepaso/src/ErrepasoAriketak/a03/Idiazabal.txt";

        // Crea un BufferedReader para leer el archivo original de forma eficiente
        // FileReader abre el archivo, BufferedReader lo lee con buffer
        BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal));

        // Crea un BufferedWriter para escribir en el archivo temporal
        // FileWriter crea/abre el archivo, BufferedWriter escribe con buffer
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal));

        // Variable para almacenar cada línea que leemos
        String linea;

        // Bucle que lee línea por línea hasta llegar al final del archivo (null)
        while ((linea = reader.readLine()) != null) {

            // Reemplaza todas las letras "a" por "?" en la línea actual
            linea = linea.replace("a","?");

            // Escribe la línea modificada en el archivo temporal
            writer.write(linea);

            // Añade un salto de línea después de escribir
            writer.newLine();
        }

        // Cierra el BufferedReader liberando recursos
        reader.close();

        // Cierra el BufferedWriter y asegura que todo se escriba al disco
        writer.close();

        // Crea un objeto File para manejar el archivo original
        File original = new File(archivoOriginal);

        // Crea un objeto File para manejar el archivo temporal
        File temporal = new File(archivoTemporal);

        // Elimina el archivo original del disco
        original.delete();

        // Renombra el archivo temporal con el nombre del original
        // Resultado: el archivo temporal ahora tiene el nombre del original
        temporal.renameTo(original);
    }
}