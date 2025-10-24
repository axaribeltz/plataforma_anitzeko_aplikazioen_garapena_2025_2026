package _3_gaia_2_ariketa;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ZerbitzariaUDP {
	public static void main(String[] args) throws InterruptedException {
		try (DatagramSocket socket = new DatagramSocket(9000)) {
			System.out.println("UDP zerbitzaria martxan 9000 portuan...");

			while (true) {
				byte[] jasotzekoDatuak = new byte[1024];
				DatagramPacket jasotzekoPacket = new DatagramPacket(jasotzekoDatuak, jasotzekoDatuak.length);

				socket.receive(jasotzekoPacket);

				new Thread(() -> {
					try {
						String mezua = new String(jasotzekoPacket.getData(), 0, jasotzekoPacket.getLength());
						System.out.println("Jasotzen dudan: " + mezua + " (" + jasotzekoPacket.getAddress() + ":"
								+ jasotzekoPacket.getPort() + ")");

						String erantzuna = mezua.toUpperCase(); // maiuskulak
						byte[] bidaltzekoDatuak = erantzuna.getBytes();

						DatagramPacket bidaltzekoPaketea = new DatagramPacket(bidaltzekoDatuak, bidaltzekoDatuak.length,
								jasotzekoPacket.getAddress(), jasotzekoPacket.getPort());
						socket.send(bidaltzekoPaketea);
						System.out.println("Erantzuna bidalita: " + erantzuna);
					} catch (IOException e) {
						System.err.println("Errorea bidaltzean/prozesatzean: " + e.getMessage());
					}
				}, "Prozesatu-" + System.currentTimeMillis()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}