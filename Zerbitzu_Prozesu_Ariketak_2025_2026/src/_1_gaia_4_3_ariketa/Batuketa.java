package _1_gaia_4_3_ariketa;

public class Batuketa {

    public static void main(String[] args) {
    	long hasierakoDenbora = System.currentTimeMillis();    	
    	
        long pid = ProcessHandle.current().pid();
        System.out.println("Nire PID da: " + pid + " - Sarrerak: " + args[0] + ", " + args[1]);
        System.out.println("Nire PID da: " + pid + " - Emaitza: " +  batura(args));
        
        long amaierakoDenbora = System.currentTimeMillis();
        long iraupenaMilisegundotan = amaierakoDenbora - hasierakoDenbora;
        long iraupenaSegundutan = iraupenaMilisegundotan/1000;
   
		System.out.printf("Prozesuak iraun duen denbora: " + iraupenaSegundutan + " segundu");
    }
    public static long batura(String[] args) {
        long emaitza=0;
        for (int i=Integer.valueOf(args[0]);i<=Integer.valueOf(args[1]);i++){
        	emaitza+=i;
        }
        return emaitza; 
    }
}