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
        try {
            serverSocket = new ServerSocket(35000);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        System.out.println("Listo para recibir ...");
        while (true) {
            Socket clientSocket;
            clientSocket = serverSocket.accept();
            pool.submit(new ConsumerPeticiones(clientSocket));
        }

    }
}
