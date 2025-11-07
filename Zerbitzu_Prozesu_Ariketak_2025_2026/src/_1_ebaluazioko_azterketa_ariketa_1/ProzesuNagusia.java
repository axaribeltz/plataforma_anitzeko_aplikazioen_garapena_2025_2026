package _1_ebaluazioko_azterketa_ariketa_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;
/**
 * Klase honek egingo duena da: A Prozesua abiaratu
 */
public class ProzesuNagusia {

    public static void main(String[] args) {
        try {
        	// A prozesua abiarazi
            ProcessBuilder processBuilderA = new ProcessBuilder("java", "-cp", "bin", "_1_ebaluazioko_azterketa_ariketa_1.ProzesuA");
            Process processA = processBuilderA.start();
            System.out.println("NAGUSIA: A Prozesua abiarazi da.");
            OutputStreamWriter outputStreamWriterA = new OutputStreamWriter(processA.getOutputStream());
            BufferedReader bufferedReaderA = new BufferedReader(new InputStreamReader(processA.getInputStream()));
            
            // B prozesua abiarazi
            ProcessBuilder processBuilderB = new ProcessBuilder("java", "-cp", "bin", "_1_ebaluazioko_azterketa_ariketa_1.ProzesuB");
            Process processB = processBuilderB.start();
            OutputStreamWriter outputStreamWriterB = new OutputStreamWriter(processB.getOutputStream());
            BufferedReader bufferedReaderB = new BufferedReader(new InputStreamReader(processB.getInputStream()));
            
            int zenbakia = 1;
            
            while (true) {
                // a) 1 zenbakia pantailan bistaratu eta bidali A prozesuari.
                System.out.println("Prozesu nagusia: A-ri bidaltzen: " + zenbakia);
                outputStreamWriterA.write(zenbakia + "\n");
                outputStreamWriterA.flush();
                

                // b) A prozesuaren erantzuna jaso eta pantailan bistaratu.
                String aErantzuna = bufferedReaderA.readLine();
                System.out.println("Prozesu nagusia: A-ren erantzuna jaso: " + aErantzuna);

                // c) Erantzun hori B prozesuari bidali.
                System.out.println("Prozesu nagusia: B-ri bidaltzen: " + aErantzuna);
                outputStreamWriterB.write(aErantzuna + "\n");
                outputStreamWriterB.flush();

                // d) B prozesuaren erantzuna jaso eta pantailan bistaratu.
                String bErantzuna = bufferedReaderB.readLine();
                System.out.println("Prozesu nagusia: B-ren erantzuna jaso: " + bErantzuna);

                // e) 2 segundu itxaron eta a) puntura bueltatu baina hurrengo zenbakia bidaliz
                TimeUnit.SECONDS.sleep(2);
                zenbakia++;
            }

        } catch (IOException | InterruptedException e) {
        	System.err.println("Errorea prozesu nagusia metodoan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}