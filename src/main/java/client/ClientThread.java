package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket socket;

    private PrintWriter out;
    private BufferedReader in;

    public ClientThread(Socket socket) {
        try {
            this.socket = socket;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String s = in.readLine();
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void send(String message) {
        out.println(message);
    }
}
