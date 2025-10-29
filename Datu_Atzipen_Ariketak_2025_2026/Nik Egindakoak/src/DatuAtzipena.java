import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class DatuAtzipena  {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Nik Egindakoak\\src\\Idiazabal_gazta.txt");
            FileReader fileReader = new FileReader(file); // A stream that connects to the text file
            BufferedReader bufferedReader = new BufferedReader(fileReader); // Connect the FileReader to the BufferedReader

            ArrayList<String> lines = new ArrayList<String>(); // Create an ArrayList object
            String line;

            while((line = bufferedReader.readLine()) != null) {
                char oldCharacter = 'a'; // replacing character
                char newCharacter = '?'; // character to be replaced
                lines.add(line.replace(oldCharacter, newCharacter));
            }

            // Revertir el orden de las líneas
            Collections.reverse(lines);

            // Construir el string con el orden invertido
            StringBuilder updatedString = new StringBuilder();
            for (String linea : lines) {
                updatedString.append(linea).append("\n");
            }

            System.out.println(updatedString.toString());

            FileWriter fileWriter = new FileWriter(file); // A stream that connects to the text file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // Connect the FileWriter to the BufferedWriter
            bufferedWriter.close();
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}