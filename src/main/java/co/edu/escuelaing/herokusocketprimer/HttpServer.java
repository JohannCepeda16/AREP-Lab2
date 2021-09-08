package co.edu.escuelaing.herokusocketprimer;

import co.edu.escuelaing.herokusocketprimer.model.ConsumerPeticiones;
import co.edu.escuelaing.herokusocketprimer.model.Interprete;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        ExecutorService pool = null;
        boolean running = true;
        int port = getPort();
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        System.out.println("Listo para recibir ...");
        while (running) {
            Socket clientSocket;
            clientSocket = serverSocket.accept();
            pool.submit(new ConsumerPeticiones(clientSocket));
        }

        serverSocket.close();
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }

        return 36000; // returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
