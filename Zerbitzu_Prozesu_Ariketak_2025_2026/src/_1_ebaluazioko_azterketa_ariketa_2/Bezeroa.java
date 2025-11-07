package _1_ebaluazioko_azterketa_ariketa_2;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Bezeroa {
    public static void main(String[] args) {
        // Se usa try-with-resources para crear el socket UDP del cliente.
        // Esto asegura que se cierre automáticamente al finalizar.
        try (DatagramSocket socket = new DatagramSocket()) {

            // Dirección del servidor (en este caso localhost)
            InetAddress address = InetAddress.getByName("localhost");
            System.out.println("Bezeroa abiarazita!"); // Mensaje de inicio

            // 🔹 Se crea un hilo (bidaliHaria = hilo que envía mensajes)
            Thread bidaliHaria = new Thread(() -> {
                try {
                    int mezuZenbakia = 1; // Contador de mensajes
                    // Enviará 10 mensajes (del 1 al 10)
                    while (!Thread.interrupted() && mezuZenbakia < 11) {
                       
                        // Crea el texto del mensaje
                        String mezua = "Mezu zenbakia: " + mezuZenbakia++;

                        // Convierte el texto en bytes para poder enviarlo
                        byte[] bidaltzekoDatuak = mezua.getBytes();

                        // Crea un paquete UDP con los datos, la longitud, la dirección y el puerto del servidor (9100)
                        DatagramPacket bidaltzekoPaketea = new DatagramPacket(
                            bidaltzekoDatuak,
                            bidaltzekoDatuak.length,
                            address,
                            9100
                        );

                        // Envía el paquete al servidor
                        socket.send(bidaltzekoPaketea);
                        System.out.println("Bidalita: " + mezua); // Confirmación por consola

                        // Espera 2 segundos antes de enviar el siguiente mensaje
                        Thread.sleep(2000);
                    }
                } catch (IOException | InterruptedException e) {
                    // Si el hilo se interrumpe o hay un error al enviar, se informa
                    System.out.println("Haria gelditu da: " + e.getMessage());
                }
            });

            // Se inicia el hilo que envía mensajes
            bidaliHaria.start();

            // 🔹 Bucle principal: el cliente queda escuchando respuestas del servidor
            while (true) {
                // Prepara un buffer de bytes para recibir los datos
                byte[] jasotzekoDatuak = new byte[1024];

                // Crea el paquete donde se almacenará la respuesta recibida
                DatagramPacket jasotzekoPacket = new DatagramPacket(jasotzekoDatuak, jasotzekoDatuak.length);

                // Espera a recibir una respuesta del servidor (bloquea la ejecución hasta que llega algo)
                socket.receive(jasotzekoPacket);

                // Convierte los bytes recibidos a texto (solo los que realmente se recibieron)
                String erantzuna = new String(jasotzekoPacket.getData(), 0, jasotzekoPacket.getLength());

                // Muestra la respuesta del servidor por consola
                System.out.println("Zerbitzariaren erantzuna: " + erantzuna);
            }

        } catch (IOException e) {
            // Manejo de errores en caso de fallo del socket o red
            e.printStackTrace();
        }
    }
}
