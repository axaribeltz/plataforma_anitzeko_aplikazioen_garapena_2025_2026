package _1_gaia_3_4_ariketa;

import java.io.IOException;

public class ItxaronAbiarazlea {
	public static void main(String[] args) {
		try {
			// Zehaztu classpath-a ("bin" Eclipse erabiltzean adibidez, moldatu beharrrezkoa bada)
			String classpath = "bin"; // `.` karpeta berdinean bada
			
			String[] segunduak = args[0].split(",");
			for (String parametroa : segunduak) {
				// Batuketa klasea abiarazi, paketea zehaztuz
				ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", classpath,
						"_1_gaia_3_4_ariketa.Itxaron", parametroa);
				// Sarrera eta irteera jarauntsi (heredar)
				processBuilder.inheritIO();
				// Prozesua abiarazi
				Process process = processBuilder.start();
				// Itxaron prozesua bukatu arte
				//int exitCode_1 = process_1.waitFor();
				//System.out.println("\n 1 Prozesua bukatu da irteera kode honekin: " + exitCode_1);
			}			
		} catch (IOException e) {
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