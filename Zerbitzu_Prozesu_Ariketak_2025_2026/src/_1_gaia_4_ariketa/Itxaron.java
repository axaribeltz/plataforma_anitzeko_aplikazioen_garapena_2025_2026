package _1_gaia_4_ariketa;
/**
 * Tarte batean dauden zenbakien arteko batura egiten du
 * Zenbaki horiek programaren lehen bi parametro bezala pasatuko zaizkio
 */
public class Itxaron {

    public static void main(String[] args) throws Exception {
    	//Prozesuaren IDa lortu
        long pid = ProcessHandle.current().pid();
        System.out.println("Nire PID da: " + pid + " - Sarrerak: " + args[0]);
        Thread.sleep(Integer.valueOf(args[0]) * 1000);
        System.out.println("Nire PID da: " + pid + " - Bukatu da");
    }
}