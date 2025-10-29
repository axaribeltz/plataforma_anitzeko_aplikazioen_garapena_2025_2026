package _3_gaia_2_ariketa;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BezeroaUDP {
	public static void main(String[] args) {
		try (DatagramSocket socket = new DatagramSocket()) {
			InetAddress address = InetAddress.getByName("localhost");
			System.out.println("Bezeroa abiarazi da");

			Thread bidaltzaileHaria = new Thread(() -> {
				int kontagailua = 1;
				while (!Thread.currentThread().isInterrupted()) {
					try {
						String mezua = "Kontagailua #" + kontagailua++;
						byte[] bidaltzekoDatuak = mezua.getBytes();
						DatagramPacket bidaltzekoPaketea = new DatagramPacket(bidaltzekoDatuak, bidaltzekoDatuak.length,
								address, 9000);
						socket.send(bidaltzekoPaketea);
						System.out.println("Datagrama bidalita: " + mezua);

						Thread.sleep(10_000); // 10sg
					} catch (IOException e) {
						System.err.println("Errorea bidaltzean: " + e.getMessage());
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}, "Bidaltzailea");

			Thread jasotzaileHaria = new Thread(() -> {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						byte[] jasotzekoDatuak = new byte[1024];
						DatagramPacket jasotzekoPacket = new DatagramPacket(jasotzekoDatuak, jasotzekoDatuak.length);
						socket.receive(jasotzekoPacket); 

						String erantzuna = new String(jasotzekoPacket.getData(), 0, jasotzekoPacket.getLength());
						System.out.println("Zerbitzariaren erantzuna: " + erantzuna);
					} catch (IOException e) {
						System.err.println("Errorea jasotzean: " + e.getMessage());
					}
				}
			}, "Jasotzailea");

			bidaltzaileHaria.start();
			jasotzaileHaria.start();
			bidaltzaileHaria.join();
			jasotzaileHaria.join();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
