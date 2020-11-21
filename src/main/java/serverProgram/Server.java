package serverProgram;

import assets.IPort;

import java.net.ServerSocket;

public class Server implements IPort {

    ServerSocket serverSocket;
    Game game;
    ServerProtocol serverProtocol;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            this.serverSocket = serverSocket;
            this.serverProtocol = new ServerProtocol();
            System.out.println("BestQuizEverServer is Running on Port " + PORT + "....");


            while (!serverSocket.isClosed()) {
                game = new Game();
                serverProtocol = new ServerProtocol();
                for (int i = 1; i < 3; i++) {
                   ServerListner serverListner = new ServerListner(serverSocket.accept(), serverProtocol, game, i);
                   System.out.println("Player " + i);
                   serverListner.start();
                }
                System.out.println("Game created\nWith " + serverProtocol.getRoundsPerGame() + " Rounds\nAnd " + serverProtocol.getQuestionsPerRound() + " questions");
            }

        }catch (Exception e) {
            System.out.println("Player disconnected");
        }
    }

    public static void main(String[] args) {
        new Server();
    }


}
