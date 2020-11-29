package clientProgram;

import model.IpConfigImp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class PlayerListener implements Runnable, IpConfigImp {

    private Socket socket;
    private ObjectOutputStream out;

    public PlayerListener(String hostName, int portNr) {
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
        PlayerProtocol playerProtocol = new PlayerProtocol();
        Thread thread = new Thread(() -> {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                Object objectFromServer;
                while ((objectFromServer = in.readObject()) != null) {
                    playerProtocol.checkObjectFromServer(objectFromServer);
                }

            } catch (IOException | ClassNotFoundException e){
                System.out.println("Server disconnected");
            }
        });
        thread.start();
    }

    public void sendObjectToServer(Object objectToServer) {
        try {
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
