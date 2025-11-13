package _1_ebaluazioko_azterketa_ariketa_1_2;

import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;


/* Sí, puedes usar inheritIO() y redirectInput(Redirect.PIPE), pero tienes que entender qué hace cada una:

inheritIO() → redirige la entrada/salida del proceso hijo al mismo terminal que el padre.
👉 No podrás comunicarte por getInputStream() ni getOutputStream() porque la E/S ya está heredada.

redirectInput(Redirect.PIPE) → crea un canal de entrada desde el proceso padre (es decir, lo que tú quieres usar con getOutputStream()).

Por tanto, no puedes usar ambas al mismo tiempo (se contradicen).
Si quieres comunicarte desde ProzesuNagusia con ProzesuA y ProzesuB, necesitas usar Redirect.PIPE y no inheritIO(). */


public class ProzesuNagusia {

    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilderA = new ProcessBuilder("java", "-cp", "bin", "_1_ebaluazioko_azterketa_ariketa_1.ProzesuA");
            processBuilderA.redirectInput(Redirect.PIPE);
            processBuilderA.redirectOutput(Redirect.PIPE);
            processBuilderA.redirectError(Redirect.INHERIT);
            Process processA = processBuilderA.start();
            System.out.println("NAGUSIA: A Prozesua abiarazi da.");

            ProcessBuilder processBuilderB = new ProcessBuilder("java", "-cp", "bin", "_1_ebaluazioko_azterketa_ariketa_1.ProzesuB");
            processBuilderB.redirectInput(Redirect.PIPE);
            processBuilderB.redirectOutput(Redirect.PIPE);
            processBuilderB.redirectError(Redirect.INHERIT);
            Process processB = processBuilderB.start();
            System.out.println("NAGUSIA: B Prozesua abiarazi da.");

            BufferedReader brA = new BufferedReader(new InputStreamReader(processA.getInputStream()));
            BufferedWriter bwA = new BufferedWriter(new OutputStreamWriter(processA.getOutputStream()));

            BufferedReader brB = new BufferedReader(new InputStreamReader(processB.getInputStream()));
            BufferedWriter bwB = new BufferedWriter(new OutputStreamWriter(processB.getOutputStream()));

            int zenbakia = 1;

            while (true) {
                System.out.println("Prozesu nagusia: A-ri bidaltzen: " + zenbakia);
                bwA.write(zenbakia + "\n");
                bwA.flush();

                String aErantzuna = brA.readLine();
                System.out.println("Prozesu nagusia: A-ren erantzuna jaso: " + aErantzuna);

                System.out.println("Prozesu nagusia: B-ri bidaltzen: " + aErantzuna);
                bwB.write(aErantzuna + "\n");
                bwB.flush();

                String bErantzuna = brB.readLine();
                System.out.println("Prozesu nagusia: B-ren erantzuna jaso: " + bErantzuna);

                TimeUnit.SECONDS.sleep(2);
                zenbakia++;
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Errorea prozesu nagusia metodoan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
