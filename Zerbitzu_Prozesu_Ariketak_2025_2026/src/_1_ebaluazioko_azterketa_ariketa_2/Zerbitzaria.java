package _1_ebaluazioko_azterketa_ariketa_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Zerbitzaria {
	private static int eskaeraKopurua = 0; // Eskaera kopurua zenbatzen duen aldagaia

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(9100)) { // DatagramSocket berria sortu 9100 portuan
            System.out.println("Zerbitzaria abiarazita 9100 portuan..."); // Kontsolan mezua inprimatu, zerbitzaria martxan dagoela adieraziz

            Thread estatistikaHaria = new Thread(() -> { // Hari berria sortu, estatistikak inprimatzeko
                while (true) { // bukle infinitua, estatistikak etengabe inprimatzeko
                    try {
                        Thread.sleep(10000); // 10 segunduro lo egin
                        System.out.println("Orain arte jasotako eskaerak: " + eskaeraKopurua); // Kontsolan eskaera kopurua inprimatu
                    } catch (InterruptedException e) { // Salbuespena harrapatu, haria eten bada
                        break; // bukletik irten
                    }
                }
            });
            estatistikaHaria.setDaemon(true); // Haria daemon bihurtu (programa nagusia amaitzean automatikoki amaitzen da)
            estatistikaHaria.start(); // Haria abiarazi

            while (true) { // bukle infinitua, eskaerak etengabe jasotzeko
                byte[] jasotzekoDatuak = new byte[1024]; // Byte array sortu, jasotako datuak gordetzeko (1024 byteko tamaina)
                DatagramPacket jasotzekoPaketea = new DatagramPacket(jasotzekoDatuak, jasotzekoDatuak.length); // DatagramPacket berria sortu, jasotzeko datuak eta luzera zehaztuz
                socket.receive(jasotzekoPaketea); // Datuak jaso socket-etik eta jasotzekoPaketea-n gorde (blokeatzen du haria datuak jaso arte)

                eskaeraKopurua++; // Eskaera kopurua handitu

                Thread haria = new Thread(() -> { // Hari berria sortu, eskaera kudeatzeko (hari bakoitza eskaera bat kudeatzeko)
                    try {
                        String mezua = new String(jasotzekoPaketea.getData(), 0, jasotzekoPaketea.getLength()); // Jasotako datuak String-era bihurtu
                        System.out.println("Jasotako mezua: " + mezua); // Kontsolan jasotako mezua inprimatu

                        Thread.sleep(6000); // 6 segunduro lo egin (eskaera prozesatzeko denbora simulatzeko)

                        String erantzuna = mezua + " JASOTA"; // Erantzuna sortu, jasotako mezua eta "JASOTA" konkatenatuz
                        byte[] bidaltzekoDatuak = erantzuna.getBytes(); // Erantzuna byte array-era bihurtu
                        InetAddress bezeroarenHelbidea = jasotzekoPaketea.getAddress(); // Bezeroaren helbidea lortu
                        int bezeroarenPortua = jasotzekoPaketea.getPort(); // Bezeroaren portua lortu

                        DatagramPacket bidaltzekoPaketea = new DatagramPacket( // DatagramPacket berria sortu, bidaltzeko datuak, luzera, bezeroaren helbidea eta portua zehaztuz
                                bidaltzekoDatuak,
                                bidaltzekoDatuak.length,
                                bezeroarenHelbidea,
                                bezeroarenPortua
                        );

                        socket.send(bidaltzekoPaketea); // Erantzuna bidali socket-etik
                    } catch (Exception e) { // Salbuespenak harrapatu
                        System.out.println("Errorea harian: " + e.getMessage()); // Kontsolan errore mezua inprimatu
                    }
                });

                haria.start(); // Haria abiarazi
            }

        } catch (IOException e) { // Salbuespena harrapatu (adibidez, portua ezin bada lotu)
            e.printStackTrace(); // Errorea inprimatu (errorearen traza)
        }
    }
}
