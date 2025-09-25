package _1_gaia_4_3_ariketa;

import java.io.IOException;

public class BatuketaAbiarazlea {
	public static void main(String[] args) {
		try {
			// Zehaztu classpath-a ("bin" Eclipse erabiltzean adibidez, moldatu beharrrezkoa bada)
			String classpath = "bin"; // `.` karpeta berdinean bada

			// Batuketa klasea abiarazi, paketea zehaztuz
			ProcessBuilder processBuilder_1 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_4_3_ariketa.Batuketa", "1", "250000000");
			ProcessBuilder processBuilder_2 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_4_3_ariketa.Batuketa", "250000001", "500000000");
			ProcessBuilder processBuilder_3 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_4_3_ariketa.Batuketa", "500000001", "750000000");
			ProcessBuilder processBuilder_4 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_4_3_ariketa.Batuketa", "750000001", "999999999");

			// Sarrera eta irteera jarauntsi (heredar)
			processBuilder_1.inheritIO();
			processBuilder_2.inheritIO();
			processBuilder_3.inheritIO();
			processBuilder_4.inheritIO();

			// Prozesua abiarazi
			Process process_1 = processBuilder_1.start();
			Process process_2 = processBuilder_2.start();
			Process process_3 = processBuilder_3.start();
			Process process_4 = processBuilder_4.start();

			// Itxaron prozesua bukatu arte
			int exitCode_1 = process_1.waitFor();
			System.out.println("\n 1 Prozesua bukatu da irteera kode honekin: " + exitCode_1);
			int exitCode_2 = process_2.waitFor();
			System.out.println("\n 2 Prozesua bukatu da irteera kode honekin: " + exitCode_2);
			int exitCode_3 = process_3.waitFor();
			System.out.println("\n 3 Prozesua bukatu da irteera kode honekin: " + exitCode_3);
			int exitCode_4 = process_4.waitFor();
			System.out.println("\n 4 Prozesua bukatu da irteera kode honekin: " + exitCode_4);

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
