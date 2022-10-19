package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (Socket socket = new Socket("127.0.0.1", 12345)) {
            ClientThread thread = new ClientThread(socket);
            new Thread(thread).start();
            String name = br.readLine();
            String line;
            while ((line = br.readLine())!=null && !line.isBlank()) {
                 thread.send(name + " says: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
