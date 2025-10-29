package _1_gaia_3_3_ariketa;

import java.io.IOException;

public class BatuketaAbiarazlea {
	public static void main(String[] args) {
		try {
			// Zehaztu classpath-a ("bin" Eclipse erabiltzean adibidez, moldatu beharrrezkoa bada)
			String classpath = "bin"; // `.` karpeta berdinean bada

			// Batuketa klasea abiarazi, paketea zehaztuz
			ProcessBuilder processBuilder_1 = new ProcessBuilder("java", "-cp", classpath,
					"prozesuAnitzekoProgramazioa_1.Batuketa", "6", "10");
			ProcessBuilder processBuilder_2 = new ProcessBuilder("java", "-cp", classpath,
					"prozesuAnitzekoProgramazioa_1.Batuketa", "6000", "10000000");

			// Sarrera eta irteera jarauntsi (heredar)
			processBuilder_1.inheritIO();
			processBuilder_2.inheritIO();
			// Prozesua abiarazi
			Process process_2 = processBuilder_2.start();
			Process process_1 = processBuilder_1.start();
			// Itxaron prozesua bukatu arte
			int exitCode_1 = process_2.waitFor();
			System.out.println("\n 1 Prozesua bukatu da irteera kode honekin: " + exitCode_1);
			int exitCode_2 = process_1.waitFor();
			System.out.println("\n 2 Prozesua bukatu da irteera kode honekin: " + exitCode_2);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/*
// Irteera estandarra kapturatu
BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
// Erroreen irteera kapturatu
BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

String line;

// Irakurri eta idatzi irteera estandarra (baldin badago)
System.out.println("Prozesuaren mezua (baldin badago):");
while ((line = stdOutput.readLine()) != null) {
	System.out.println(line);
}
// Lerroa hutsik
System.out.println();

// Irakurri eta idatzi errore irteera (baldin badago)
System.out.println("Errore mezua (baldin badago):");
while ((line = stdError.readLine()) != null) {
	System.out.println(line);
}
*/