package _1_gaia_5_ariketa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AzkenBatura {
    public static void main(String[] args) {
        try {
        	
            // Erabiltzaileari mezua (irteera estandarra, SYSTEM.OUT)
            System.out.println("AzkenBatura Prozesua: nagusiaren emaitza itxaroten...");

            // Emaitza sarrera estandarretik irakurri (SYSTEM.IN)
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String emaitza_1 = reader.readLine();  // Prozesu Nagusiak bidali dion emaitza irakurri
            String emaitza_2 = reader.readLine();  // Prozesu Nagusiak bidali dion emaitza irakurri
            String emaitza_3 = reader.readLine();  // Prozesu Nagusiak bidali dion emaitza irakurri
            String emaitza_4 = reader.readLine();  // Prozesu Nagusiak bidali dion emaitza irakurri

            long azkenEmaitza = Long.valueOf(emaitza_1) + Long.valueOf(emaitza_2) + Long.valueOf(emaitza_3) +Long.valueOf(emaitza_4);
            
            // Erabiltzaileari mezua (irteera estandarra, SYSTEM.OUT)
            System.out.println("\nAzkenBatura Prozesua: jasotako emaitzen batura: " + azkenEmaitza);
                        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
