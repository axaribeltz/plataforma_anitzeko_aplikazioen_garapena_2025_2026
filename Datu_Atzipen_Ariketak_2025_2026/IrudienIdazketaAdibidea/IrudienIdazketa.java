package FitxategiBitarrak.IrudiBitarrak;
import java.io.FileOutputStream;
import java.io.IOException;

public class IrudienIdazketa {

	public static void main(String[] args) {
        int width = 8;
        int height = 8;

        // PPM fitxategi motarako goiburua idazten dugu. (P6)
        String header = "P6\n" + width + " " + height + "\n255\n";

        // Kolore gorriaren berezko zenbakiak (R=255, G=0, B=0)
        byte red = (byte) 0;
        byte green = (byte) 0;
        byte blue = (byte) 0;

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\2AM3-4\\Documents\\plataforma_anitzeko_aplikazioen_garapena_2025_2026\\Datu_Atzipen_Ariketak_2025_2026\\IrudienIdazketaAdibidea\\Irudia.ppm")) {
            // Fitxategiaren goiburua idazten du.
            fos.write(header.getBytes());

            // Pixel bakoitzeko iterazioa.
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Kolore gorria irudikatzeko beharrezko hiru byte-ak idazten ditu.
                    if ((y + x) % 2 == 0) {
                        fos.write(0);
                        fos.write(0);
                        fos.write(0);
                    } else {
                        fos.write(255);
                        fos.write(255);
                        fos.write(255);
                    }
                }
            }
            System.out.println("Irudi gorria ondo sortuta: irudi_gorria.ppm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}