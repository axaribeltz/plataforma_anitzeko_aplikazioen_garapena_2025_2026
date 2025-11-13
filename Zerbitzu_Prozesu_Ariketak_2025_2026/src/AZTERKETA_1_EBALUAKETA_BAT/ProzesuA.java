package AZTERKETA_1_EBALUAKETA_BAT;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;
/**
 * Klase honek egingo duena da: A Prozesua abiaratu
 */
public class ProzesuA {

    public static void main(String[] args) {
            try {
                ProcessBuilder processBuilderB = new ProcessBuilder("java", "-cp", "bin", "AZTERKETA_1_EBALUAKETA_BAT.ProzesuB");
                processBuilderB.redirectInput(Redirect.PIPE);
                processBuilderB.redirectOutput(Redirect.PIPE);
                processBuilderB.redirectError(Redirect.INHERIT);
                Process processB = processBuilderB.start();
                System.out.println("B Prozesua abiarazi da.");
                        
                int zenbakia = 1;
            
                BufferedReader brB = new BufferedReader(new InputStreamReader(processB.getInputStream()));
                BufferedWriter bwB = new BufferedWriter(new OutputStreamWriter(processB.getOutputStream()));
                
                while (true) {
                    System.out.println("B Prozesura bidali den mezua: " + zenbakia + ". ZENBAKIA");
                    bwB.write(zenbakia + "\n");
                    bwB.flush();

                    String bErantzuna = brB.readLine();
                    System.out.println("B Prozesutik jaso den mezua: " + bErantzuna);

                    TimeUnit.SECONDS.sleep(5);
                    zenbakia++;
                }

        } catch (IOException | InterruptedException e) {
        	System.err.println("Errorea A prozesu metodoan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}