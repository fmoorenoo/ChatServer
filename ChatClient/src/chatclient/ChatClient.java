package chatclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        String direccionServidor = args.length > 0 ? args[0] : "localhost";
        int puertoServidor = 9876;

        ChatClient cliente = new ChatClient(direccionServidor, puertoServidor);
        cliente.iniciar();
    }

    private final String address;
    private final int port;
    private String username;

    public ChatClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void iniciar() {
        try (Socket socket = new Socket(address, port)) {
            System.out.println("Conectado al servidor en " + address + ":" + port);
            new Thread(new ReceptorMensajes(socket)).start();
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextLine()) {
                System.out.println("No se introdujo un nombre de usuario. Cerrando conexión.");
                return;
            }
            username = scanner.nextLine();
            salida.println(username);
            while (scanner.hasNextLine()) {
                salida.println(scanner.nextLine());
            }

        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    private static class ReceptorMensajes implements Runnable {

        private final Socket socket;

        public ReceptorMensajes(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println(mensaje);
                }
            } catch (IOException e) {
                System.err.println("Error al recibir mensajes: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

}
