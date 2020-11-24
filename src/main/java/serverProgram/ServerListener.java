package serverProgram;

import Model.InfoObj;
import Model.Player;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerListener extends Thread{

    private final Player player = new Player();
    private final Socket socket;
    private final ServerProtocol serverProtocol;
    private final Game game;
    private ObjectOutputStream objOut;


    public ServerListener(Socket socket, ServerProtocol serverProtocol, Game game, int i){
        this.socket = socket;
        this.serverProtocol = serverProtocol;
        this.game = game;
        try {
        if (i == 1){
            game.setPlayer1(player);
            game.setCurrentPlayer(player);
        }else {
            game.setPlayer2(player);
            game.setNotCurrentPlayer(player);
        }
            this.objOut = new ObjectOutputStream(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        serverProtocol.getPlayersList().add(this);
    }

    @Override
    public void run() {
        try (ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream())){
            Object object;
                try {
                    while ((object = objIn.readObject()) != null) {
                        System.out.println("Object received from " + this.player.getPlayerName() + object);
                        serverProtocol.handleObject(this, (InfoObj) object);
                    }

                }catch (EOFException e){
                    e.printStackTrace();
                }
        }catch (SocketException | EOFException e){
            System.out.println("Player disconnected");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendObj(Object obj){
        try {
            System.out.println("sending obj to client " + this.player.getPlayerName());
            objOut.writeObject(obj);
            objOut.flush();
        }catch (Exception e){
            System.out.println("Couldn't send object");
            e.printStackTrace();
        }
    }

    public Game getGame(){
        return game;
    }

    public Player getPlayer() {
        return player;
    }
}
