package _1_ebaluazioko_azterketa_ariketa_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProzesuB {
    public static void main(String[] args) {
        try {
        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    int zenbakia = Integer.parseInt(line);
                    int emaitza = zenbakia + 1;
                    outputStreamWriter.write(emaitza + "\n");
                    outputStreamWriter.flush();
                } catch (NumberFormatException e) {
                    System.err.println("Errorea zenbakia interpretatzen: " + line);
                }
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
