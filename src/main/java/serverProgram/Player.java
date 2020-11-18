package serverProgram;

import Model.InfoObj;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player extends Thread{

    private Socket socket;
    private ObjectOutputStream objout;
    private ObjectInputStream objin;
    private ServerProtocol serverProtocol;
    private Game game;
    private String playerName;
    private boolean readuToPlay = false;

    public Player(Socket socket, ServerProtocol serverProtocol){
        this.socket = socket;
        this.serverProtocol = serverProtocol;
        serverProtocol.getPlayersList().add(this);

    }

    @Override
    public void run() {
        try (ObjectOutputStream objout = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objin = new ObjectInputStream(socket.getInputStream())){
            this.objout = objout;
            this.objin = objin;

            Object obj;
            while (!socket.isClosed()){
                try {
                    obj = objin.readObject();
                    serverProtocol.handleObject(this, (InfoObj) obj);
                }catch (EOFException e){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendObj(Object obj){
        try {
            objout.writeObject(obj);
        }catch (Exception e){
            System.out.println("Coulden't send object");
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getObjout() {
        return objout;
    }

    public void setObjout(ObjectOutputStream objout) {
        this.objout = objout;
    }

    public ObjectInputStream getObjin() {
        return objin;
    }

    public void setObjin(ObjectInputStream objin) {
        this.objin = objin;
    }

    public ServerProtocol getServerProtocol() {
        return serverProtocol;
    }

    public void setServerProtocol(ServerProtocol serverProtocol) {
        this.serverProtocol = serverProtocol;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isReaduToPlay() {
        return readuToPlay;
    }

    public void setReaduToPlay(boolean readuToPlay) {
        this.readuToPlay = readuToPlay;
    }
}
