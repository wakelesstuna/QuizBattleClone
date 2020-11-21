package serverProgram;

import Model.InfoObj;
import Model.Player;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerListner extends Thread{

    private Player player = new Player();
    private final Socket socket;
    private ObjectOutputStream objout;
    private ServerProtocol serverProtocol;
    private Game game;


    public ServerListner(Socket socket, ServerProtocol serverProtocol, Game game, int i){
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

            this.objout = new ObjectOutputStream(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        serverProtocol.getPlayersList().add(this);
    }

    @Override
    public void run() {
        try (ObjectInputStream objin = new ObjectInputStream(socket.getInputStream())){
            Object obj;
            while (true){
                try {

                    obj = objin.readObject();
                    System.out.println("Object recevied from " + this.player.getPlayerName() + obj);
                    if (obj instanceof InfoObj)
                        serverProtocol.handleObject(this, (InfoObj) obj);
                }catch (EOFException e){
                    e.printStackTrace();
                }
            }
        }catch (SocketException e){
            System.out.println("Player disconnected");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendObj(Object obj){
        try {
            System.out.println("sending obj to cleint " + this.player.getPlayerName());
            objout.writeObject(obj);
            objout.flush();
        }catch (Exception e){
            System.out.println("Coulden't send object");
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
