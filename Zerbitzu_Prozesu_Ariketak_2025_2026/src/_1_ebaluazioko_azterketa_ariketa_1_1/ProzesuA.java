package _1_ebaluazioko_azterketa_ariketa_1_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProzesuA {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int zenbakia = Integer.parseInt(line);
                    int emaitza = zenbakia * 2;
                    bw.write(emaitza + "\n");
                    bw.flush();
                } catch (NumberFormatException e) {
                    System.err.println("Errorea zenbakia interpretatzen: " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
