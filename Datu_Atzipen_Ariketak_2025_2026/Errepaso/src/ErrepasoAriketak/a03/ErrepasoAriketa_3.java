package ErrepasoAriketak.a03;

import java.io.*;

public class ErrepasoAriketa_3 {
    public static void main(String[] args) throws IOException {
        String archivoOriginal = "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Errepaso\\src\\ErrepasoAriketak\\Idiazabal.txt";
        String archivoTemporal = "C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Errepaso\\src\\ErrepasoAriketak\\Idiazabal_temp.txt";

        BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal));
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal));

        String linea;
        while ((linea = reader.readLine()) != null) {
            linea = linea.replace("a","?");
            writer.write(linea);
            writer.newLine();
        }

        reader.close();
        writer.close();

        // Reemplazar el archivo original con el temporal
        File original = new File(archivoOriginal);
        File temporal = new File(archivoTemporal);

        original.delete();
        temporal.renameTo(original);
    }
}
