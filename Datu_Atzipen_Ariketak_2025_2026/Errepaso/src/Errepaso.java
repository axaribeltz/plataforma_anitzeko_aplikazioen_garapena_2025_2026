import java.io.*;
import java.util.ArrayList;

public class Errepaso {
    public static void main(String[] args) throws IOException {
        //TEXTUA IRAKURRI eta MAYUSKULAK MINUSKULA jarri eta MINUSKULAK MAYUSKULA
        File file = new File("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\Errepaso\\src\\Idiazabal.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            StringBuilder swapped = new StringBuilder();

            for (char c : line.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    swapped.append(Character.toLowerCase(c));
                } else if (Character.isLowerCase(c)) {
                    swapped.append(Character.toUpperCase(c));
                } else {
                    swapped.append(c);
                }
            }
            lines.add(swapped.toString());
        }

        bufferedReader.close();

        StringBuilder updatedString = new StringBuilder();
        for (String linea : lines) {
            updatedString.append(linea).append("\n");
        }

        System.out.println(updatedString.toString());
    }
}
