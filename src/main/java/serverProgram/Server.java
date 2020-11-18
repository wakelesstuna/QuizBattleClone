package serverProgram;

import assets.IPort;

import java.net.ServerSocket;

public class Server implements IPort {

    private int playersPerGame = 2;
    ServerSocket serverSocket;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            this.serverSocket = serverSocket;
            System.out.println("BestQuizEverServer is Running on Port " + PORT);
            Game game = new Game();
            ServerProtocol serverProtocol = new ServerProtocol();

            while (!serverSocket.isClosed()) {
                for (int i = 1; i < playersPerGame + 1; i++) {
                    new Player(serverSocket.accept(), serverProtocol, game, i);
                    System.out.println("Player " + i);
                }
            }

        }catch (Exception e) {
            System.out.println("Player disconnected");
        }
    }

    public static void main(String[] args) {
        new Server();
    }


}
