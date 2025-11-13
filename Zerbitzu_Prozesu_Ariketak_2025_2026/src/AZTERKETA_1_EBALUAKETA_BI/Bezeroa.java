package AZTERKETA_1_EBALUAKETA_BI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klase hau makina lokalean 5000 portuan entzuten dagoen zerbitzari batera
 * konektatzen da 10 aldiz, aldi bakoitzean zenbaki bat bidaliz

 * Gero 5001 portura koentatzen da behin, eta erantzuna itxaroten du.
 * Erantzun hau baturaren emaitza da eta bistaratu egiten du.
 */
public class Bezeroa {
	public static void main(String[] args) {
		// Hainbat bezero simulatu
		int bezeroKopurua = 10;
		Thread[] bezeroak = new Thread[bezeroKopurua];

		for (int i = 0; i < bezeroKopurua; i++) {
			// 1 eta 10 arteko ausazko zenbakiak bidaliko dira
			final int zenbakia = (int) (Math.random() * 10) + 1;
			System.out.println("Bidalitako zenbakia: " + zenbakia);
			bezeroak[i] = new Thread(() -> {
				zerbitzariraZenbakiaBidali(zenbakia); // 5000 portura bidali
			});
			bezeroak[i].start();
		}

		// Itxaron hari guztiak bukatu arte
		for (Thread t : bezeroak) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Itxaron 5 segundu
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Zerbitzarira konektatu emaitza eskatu eta bistaratzeko
		zerbitzaritikEmaitzaLortu(); // 5001 portura
	}

	private static void zerbitzariraZenbakiaBidali(int zenbakia) {
		try (Socket socket = new Socket("localhost", 5000)) {
			// Sortu sarrera eta irteera Stream-ak
			PrintWriter irteera = new PrintWriter(socket.getOutputStream(), true);

			// Bidali zenbakia zerbitzariari
			irteera.println(zenbakia);

		} catch (IOException e) {
			System.err.println("Errorea bezeroaExekutatu funtzioan: " + e.getMessage());
		}
	}
	
	private static void zerbitzaritikEmaitzaLortu() {
		try (Socket socket = new Socket("localhost", 5001)) {
			// Sortu sarrera eta irteera Stream-ak
			//PrintWriter irteera = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader sarrera = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Irakurri zerbitzariaren erantzuna
			String erantzuna = sarrera.readLine();
			System.out.println("Emaitza: " + erantzuna);

		} catch (IOException e) {
			System.err.println("Errorea bezeroan: " + e.getMessage());
		}
	}
}