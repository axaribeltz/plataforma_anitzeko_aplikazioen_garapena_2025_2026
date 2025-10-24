package FitxategiBitarrak.IrudiBitarrak;
import java.io.FileOutputStream;
import java.io.IOException;

public class IrudienIdazketa {

	public static void main(String[] args) {
        int width = 256;
        int height = 256;

        // PPM fitxategi motarako goiburua idazten dugu. (P6)
        String header = "P6\n" + width + " " + height + "\n255\n";

        // Kolore gorriaren berezko zenbakiak (R=255, G=0, B=0)
        byte red = (byte) 255;
        byte green = (byte) 0;
        byte blue = (byte) 0;

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\aelorza\\eclipse-workspace\\Fitxategiak\\src\\FitxategiBitarrak\\IrudiBitarrak\\irudi_gorria.ppm")) {
            // Fitxategiaren goiburua idazten du.
            fos.write(header.getBytes());

            // Pixel bakoitzeko iterazioa.
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Kolore gorria irudikatzeko beharrezko hiru byte-ak idazten ditu.
                    fos.write(red);
                    fos.write(green);
                    fos.write(blue);
                }
            }
            System.out.println("Irudi gorria ondo sortuta: irudi_gorria.ppm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}