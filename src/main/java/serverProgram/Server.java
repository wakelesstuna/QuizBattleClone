package serverProgram;

import assets.IPort;

import java.net.ServerSocket;

public class Server implements IPort {

    ServerSocket serverSocket;
    Game game = new Game();
    ServerProtocol serverProtocol = new ServerProtocol();

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            this.serverSocket = serverSocket;
            System.out.println("BestQuizEverServer is Running on Port " + PORT + "....");


            while (!serverSocket.isClosed()) {
                int playersPerGame = 2;
                for (int i = 1; i < playersPerGame + 1; i++) {
                   new Player(serverSocket.accept(), serverProtocol, game, i);
                   System.out.println("Player " + i);

                }

                //Player player1 = new Player(serverSocket.accept(), serverProtocol, game, 1);
                //Player player2 = new Player(serverSocket.accept(), serverProtocol, game, 2);
                System.out.println(serverProtocol.getPlayersList().size());
                for (Player p : serverProtocol.getPlayersList()) {
                    System.out.println(p.getObjout());
                }
                //player1.start();
                //player2.start();
            }

        }catch (Exception e) {
            System.out.println("Player disconnected");
        }
    }

    public static void main(String[] args) {
        new Server();
    }


}
