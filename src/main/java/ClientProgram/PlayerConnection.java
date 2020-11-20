package ClientProgram;

import assets.IPort;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerConnection implements Runnable, IPort {

    Socket socket;
    ObjectOutputStream out;

    public PlayerConnection(String hostName, int portNr){
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
        receiveObjectFromServer();
    }

    private void receiveObjectFromServer() {
        PlayerProtocol playerProtocol = new PlayerProtocol();
        Thread thread = new Thread(() -> {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Object objectFromServer;
                while ((objectFromServer = in.readObject()) != null){
                    System.out.println("Object received for sorting");
                    System.out.println(objectFromServer.toString());
                    playerProtocol.checkObjectFromServer(objectFromServer);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void sendObjectToServer(Object objectToServer){
        try {
            System.out.println("Sending object to server...");
            out.writeObject(objectToServer);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PlayerConnection("127.0.0.1",PORT);
    }
}
