package _1_gaia_5_ariketa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;

public class BatuketaAbiarazlea {
	public static void main(String[] args) {
		try {
			// Zehaztu classpath-a ("bin" Eclipse erabiltzean adibidez, moldatu beharrrezkoa bada)
			String classpath = "bin"; // `.` karpeta berdinean bada

			// Batuketa klasea abiarazi, paketea zehaztuz
			ProcessBuilder processBuilder_1 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_5_ariketa.Batuketa", "1", "250000000");
			ProcessBuilder processBuilder_2 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_5_ariketa.Batuketa", "250000001", "500000000");
			ProcessBuilder processBuilder_3 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_5_ariketa.Batuketa", "500000001", "750000000");
			ProcessBuilder processBuilder_4 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_5_ariketa.Batuketa", "750000001", "999999999");
			
			ProcessBuilder processBuilder_5 = new ProcessBuilder("java", "-cp", classpath,
					"_1_gaia_5_ariketa.AzkenBatura");
			

			// Sarrera eta irteera jarauntsi (heredar)
			// Erroreen irteera PIPE moduan jarri (hau erabiliko dugu emaitza jasotzeko)
			processBuilder_1.inheritIO();
			processBuilder_1.redirectError(Redirect.PIPE);
			processBuilder_2.inheritIO();
			processBuilder_2.redirectError(Redirect.PIPE);
			processBuilder_3.inheritIO();
			processBuilder_3.redirectError(Redirect.PIPE);
			processBuilder_4.inheritIO();
			processBuilder_4.redirectError(Redirect.PIPE);	
			
			processBuilder_5.inheritIO();
			processBuilder_5.redirectInput(Redirect.PIPE);
			
			// Prozesua abiarazi
			Process process_1 = processBuilder_1.start();
			Process process_2 = processBuilder_2.start();
			Process process_3 = processBuilder_3.start();
			Process process_4 = processBuilder_4.start();
			
			Process process_AzkenBatura = processBuilder_5.start();
			
			// Emaitza jaso (getErrorStream) A Prozesutik
            BufferedReader errorAReader = new BufferedReader(new InputStreamReader(process_1.getErrorStream()));
            String resultA = errorAReader.readLine();
            System.out.println("\nNAGUSIA: A Prozesutik lortu den emaitza: " + resultA);
            
            // Emaitza jaso (getErrorStream) B Prozesutik
            BufferedReader errorBReader = new BufferedReader(new InputStreamReader(process_2.getErrorStream()));
            String resultB = errorBReader.readLine();
            System.out.println("NAGUSIA: B Prozesutik lortu den emaitza: " + resultB);
            
            // Emaitza jaso (getErrorStream) C Prozesutik
            BufferedReader errorCReader = new BufferedReader(new InputStreamReader(process_3.getErrorStream()));
            String resultC = errorCReader.readLine();
            System.out.println("NAGUSIA: C Prozesutik lortu den emaitza: " + resultC);
            
            // Emaitza jaso (getErrorStream) D Prozesutik
            BufferedReader errorDReader = new BufferedReader(new InputStreamReader(process_4.getErrorStream()));
            String resultD = errorDReader.readLine();
            System.out.println("NAGUSIA: D Prozesutik lortu den emaitza: " + resultD);

			// Itxaron prozesua bukatu arte
			int exitCode_1 = process_1.waitFor();
			System.out.println("\n 1 Prozesua bukatu da irteera kode honekin: " + exitCode_1);
			int exitCode_2 = process_2.waitFor();
			System.out.println("\n 2 Prozesua bukatu da irteera kode honekin: " + exitCode_2);
			int exitCode_3 = process_3.waitFor();
			System.out.println("\n 3 Prozesua bukatu da irteera kode honekin: " + exitCode_3);
			int exitCode_4 = process_4.waitFor();
			System.out.println("\n 4 Prozesua bukatu da irteera kode honekin: " + exitCode_4);
			
			// Lortu ditugun emaitzak AzkenBatura Prozesura pasatu (bere sarrera estandarraren bidez)
            OutputStream outputToAzkenBatura = process_AzkenBatura.getOutputStream();
            
            outputToAzkenBatura.write(resultA.getBytes());  // 1 Prozesuaren emaitza idatzi
            outputToAzkenBatura.write("\n".getBytes());  // Lerro bukaera adierazten duen karakterea bidaltzen dela ziurtatu
            outputToAzkenBatura.flush();  // Ziurtatu datuak bidali direla
            
            outputToAzkenBatura.write(resultB.getBytes());  // 2 Prozesuaren emaitza idatzi
            outputToAzkenBatura.write("\n".getBytes());  // Lerro bukaera adierazten duen karakterea bidaltzen dela ziurtatu
            outputToAzkenBatura.flush();  // Ziurtatu datuak bidali direla
            
            outputToAzkenBatura.write(resultC.getBytes());  // 3 Prozesuaren emaitza idatzi
            outputToAzkenBatura.write("\n".getBytes());  // Lerro bukaera adierazten duen karakterea bidaltzen dela ziurtatu
            outputToAzkenBatura.flush();  // Ziurtatu datuak bidali direla
            
            outputToAzkenBatura.write(resultD.getBytes());  // 4 Prozesuaren emaitza idatzi
            outputToAzkenBatura.write("\n".getBytes());  // Lerro bukaera adierazten duen karakterea bidaltzen dela ziurtatu
            outputToAzkenBatura.flush();  // Ziurtatu datuak bidali direla
            
            outputToAzkenBatura.close();  // B Prozesuaren sarrera itxi datu gehiago ez direla egongo adierazteko
            
            // Itxaron a Prozesua amaitu arte
            int exitAzkenBatura = process_AzkenBatura.waitFor();
            System.out.println("NAGUSIA: B Prozesua bukatu da irteera kode honekin: " + exitAzkenBatura);

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
