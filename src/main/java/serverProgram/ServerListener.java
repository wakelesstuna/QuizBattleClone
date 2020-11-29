package serverProgram;

import model.InfoObj;
import model.Player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerListener extends Thread {

    private final Player player = new Player();
    private final Socket socket;
    private final ServerProtocol serverProtocol;
    private final Game game;
    private ObjectOutputStream objOut;


    public ServerListener(Socket socket, ServerProtocol serverProtocol, Game game, int i) {
        this.socket = socket;
        this.serverProtocol = serverProtocol;
        this.game = game;
        if (i == 1) {
            game.setPlayer1(player);
            game.setCurrentPlayer(player);
        } else {
            game.setPlayer2(player);
            game.setNotCurrentPlayer(player);
        }
        try {
            this.objOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        serverProtocol.getPlayersList().add(this);
    }

    @Override
    public void run() {
        try (ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream())) {

            Object object;
            while ((object = objIn.readObject()) != null) {

                serverProtocol.handleObject(this, (InfoObj) object);
            }

        } catch (Exception e) {
            System.out.println(player.getPlayerName() + " disconnected from the game");
        }
    }

    public void sendObj(Object obj) {
        try {
            objOut.writeObject(obj);
            objOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }
}
