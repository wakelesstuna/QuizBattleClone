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
                   new Player(serverSocket.accept(), serverProtocol, game, i);
                   System.out.println("Player " + i);

                }

                /*System.out.println(serverProtocol.getPlayersList().size());
                for (Player p : serverProtocol.getPlayersList()) {
                    System.out.println(p.getObjout());
                }*/
            }

        }catch (Exception e) {
            System.out.println("Player disconnected");
        }
    }

    public static void main(String[] args) {
        new Server();
    }


}
