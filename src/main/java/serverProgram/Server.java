package serverProgram;

import model.IpConfigImp;

import java.net.ServerSocket;

public class Server implements IpConfigImp {

    ServerSocket serverSocket;
    Game game;
    ServerProtocol serverProtocol;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            this.serverSocket = serverSocket;

            while (!serverSocket.isClosed()) {
                game = new Game();
                serverProtocol = new ServerProtocol();
                for (int i = 1; i < 3; i++) {
                    ServerListener serverListener = new ServerListener(serverSocket.accept(), serverProtocol, game, i);
                    serverListener.start();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }

}