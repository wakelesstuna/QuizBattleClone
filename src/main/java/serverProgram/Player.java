package serverProgram;

import Model.InfoObj;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Player extends Thread{

    private Socket socket;
    private ObjectOutputStream objout;
    private ObjectInputStream objin;
    private ServerProtocol serverProtocol;
    private Game game;
    private String playerName;
    private int playerRoundScore;
    private int playerTotalScore;
    private boolean hasAnswer = false;
    private boolean readyToPlay = false;

    public Player(Socket socket, ServerProtocol serverProtocol, Game game, int i){
        this.socket = socket;
        this.serverProtocol = serverProtocol;
        this.game = game;
        if (i == 1){
            game.setPlayer1(this);
            game.setCurrentPlayer(this);
        }else {
            game.setPlayer2(this);
            game.setNotCurrentPlayer(this);
        }
        serverProtocol.getPlayersList().add(this);
        new Thread(this).start();

    }

    @Override
    public void run() {
        try (ObjectOutputStream objout = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objin = new ObjectInputStream(socket.getInputStream())){
            this.objout = objout;
            this.objin = objin;

            Object obj;
            while (true){
                try {
                    System.out.println("kom hit före inread");
                    obj = objin.readObject();
                    serverProtocol.handleObject(this, (InfoObj) obj);
                    this.sendObj(new InfoObj(STATE.GAME_OVER,"kom hit"));
                }catch (EOFException e){
                    break;
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

    public int getPlayerRoundScore() {
        return playerRoundScore;
    }

    public void setPlayerRoundScore(int playerRoundScore) {
        this.playerRoundScore = playerRoundScore;
    }

    public int getPlayerTotalScore() {
        return playerTotalScore;
    }

    public void setPlayerTotalScore(int playerTotalScore) {
        this.playerTotalScore = playerTotalScore;
    }

    public boolean isHasAnswer() {
        return hasAnswer;
    }

    public void setHasAnswer(boolean hasAnswer) {
        this.hasAnswer = hasAnswer;
    }

    public boolean isReadyToPlay() {
        return readyToPlay;
    }

    public void setReadyToPlay(boolean readyToPlay) {
        this.readyToPlay = readyToPlay;
    }
}
