package _1_gaia_4_1_ariketa;

import java.io.IOException;

public class BatuketaAbiarazlea {
	public static void main(String[] args) {
		try {
			// Zehaztu classpath-a ("bin" Eclipse erabiltzean adibidez, moldatu beharrrezkoa bada)
			String classpath = "bin"; // `.` karpeta berdinean bada

			// Batuketa klasea abiarazi, paketea zehaztuz
			ProcessBuilder processBuilder_1 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_4_1_ariketa.Batuketa", "1", "999999999");

			// Sarrera eta irteera jarauntsi (heredar)
			processBuilder_1.inheritIO();

			// Prozesua abiarazi
			Process process_1 = processBuilder_1.start();

			// Itxaron prozesua bukatu arte
			int exitCode_1 = process_1.waitFor();
			System.out.println("\n 1 Prozesua bukatu da irteera kode honekin: " + exitCode_1);

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
