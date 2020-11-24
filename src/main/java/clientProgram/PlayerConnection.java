package clientProgram;

import model.IIpConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerConnection implements Runnable, IIpConfig {

    private Socket socket;
    private ObjectOutputStream out;

    public PlayerConnection(String hostName, int portNr) {
        System.out.println("ClientConnection created");
        try {
            this.socket = new Socket(hostName, portNr);
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Running...");
        PlayerProtocol playerProtocol = new PlayerProtocol();
        Thread thread = new Thread(() -> {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Object objectFromServer;
                while ((objectFromServer = in.readObject()) != null) {
                    System.out.println("Object received for sorting");
                    playerProtocol.checkObjectFromServer(objectFromServer);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void sendObjectToServer(Object objectToServer) {
        try {
            System.out.println("Sending object to server...");
            out.writeObject(objectToServer);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
