package ErrepasoAriketak.a04;

import java.io.FileNotFoundException;
import java.io.ObjectInputFilter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RandomAccesFilePringadillo {

    static String[] zutabeak = {"ida", "izena", "dni"};
    static int[] zabalerak = {2, 10, 9};
    static int longReg = 0;
    static String fitxIzena = "randomAdibidea.dat";

    public static void main(String[] args) {
        kalkulatuLongReg();
        System.out.println("Erregistro zabalera: " +longReg);

        int[] idak = {1, 2};
        String[] izenak = {"pepe", "jose"};
        String[] dniak = {"12345678P", "87654321Q"};

        String[] erregistroa = new String[3];

		/*for (int i=0; i < idak.length; i++) {
			erregistroa[0] = Integer.toString(idak[i]);
			erregistroa[1] = izenak[i];
			erregistroa[2] = dniak[i];
			txertatu(erregistroa);
		}*/

        getErregIzena(1);
        System.out.println("Orain bilatu erregistroa:");
        bilatuErregistroaIzenarekiko("pepe");
    }

    public static void kalkulatuLongReg() {
        for (int i=0; i<zabalerak.length; i++) {
            longReg+=zabalerak[i];
        }
    }

    public static void txertatu(String[] erregistroa) {
        try(RandomAccessFile raf = new RandomAccessFile(fitxIzena, "rw")) {
            raf.seek(raf.length());
            String valorCampoForm = "";

            valorCampoForm = String.format("%1$-" + zabalerak[0] + "s",  erregistroa[0]);
            raf.write(valorCampoForm.getBytes("UTF-8"), 0, zabalerak[0]);
            valorCampoForm = String.format("%1$-" + zabalerak[1] + "s",  erregistroa[1]);
            raf.write(valorCampoForm.getBytes("UTF-8"), 0, zabalerak[1]);
            valorCampoForm = String.format("%1$-" + zabalerak[2] + "s",  erregistroa[2]);
            raf.write(valorCampoForm.getBytes("UTF-8"), 0, zabalerak[2]);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getErregIzena(int erregZkia) {
        try(RandomAccessFile raf = new RandomAccessFile(fitxIzena, "rw")) {
            raf.seek((erregZkia-1)*longReg + zabalerak[0]);
            byte[] izena = new byte[zabalerak[1]];
            raf.read(izena);

            System.out.println(new String(izena, StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void bilatuErregistroaIzenarekiko(String bilaketaIzena) {
        //ETXERAKO LANA
        try(RandomAccessFile raf = new RandomAccessFile(fitxIzena, "rw")) {
            boolean aurkitua = false;
            int erregZenbakia = 1;
            byte[] irakurritakoIzena = new byte[zabalerak[1]];
            while (!aurkitua && (erregZenbakia <= raf.length()/longReg)) {
                raf.seek((erregZenbakia-1)*longReg+zabalerak[0]);

                raf.read(irakurritakoIzena);
                if (bilaketaIzena.toLowerCase().equals(new String(irakurritakoIzena, StandardCharsets.UTF_8).trim().toLowerCase())) {
                    aurkitua = true;
                    System.out.println(bilaketaIzena + " izena, " + erregZenbakia + " erregistroan dago");
                }
                erregZenbakia++;
            }

            if (!aurkitua) {
                System.out.println("Ez da izen hori fitxategian aurkitu");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}