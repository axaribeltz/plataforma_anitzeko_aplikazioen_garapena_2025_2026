package _1_ebaluazioko_azterketa_ariketa_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

public class ProzesuB {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int zenbakia;
			String line;
			while ((line = br.readLine()) != null) {
				try {
					ProcessBuilder processBuilderC = new ProcessBuilder("java", "-cp", "bin", "_1_ebaluazioko_azterketa_ariketa_1.ProzesuC");
	                processBuilderC.redirectInput(Redirect.PIPE);
	                processBuilderC.redirectOutput(Redirect.PIPE);
	                processBuilderC.redirectError(Redirect.INHERIT);
	                Process processC = processBuilderC.start();
	                System.err.println("C Prozesua abiarazi da.");
	                
	                String mezua = "+B ESKAERA";
	                
	                BufferedReader brC = new BufferedReader(new InputStreamReader(processC.getInputStream()));
	                BufferedWriter bwC = new BufferedWriter(new OutputStreamWriter(processC.getOutputStream()));
	                
	                while (true) {
	                    System.out.println("C Prozesura bidali den mezua: " + line + ". ZENBAKIA " + mezua);
	                    bwC.write(line + ". ZENBAKIA" + mezua + "\n");
	                    bwC.flush();

	                    String cErantzuna = brC.readLine();
	                    System.out.println("c Prozesutik jaso den mezua: " + cErantzuna);
	                    
	                    bw.write(cErantzuna + ".ZENBAKIA \n");
						bw.flush();
	                }
	                					
				} catch (NumberFormatException e) {
					System.err.println("Errorea zenbakia interpretatzen: " + line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
