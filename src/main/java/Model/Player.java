package Model;

import java.io.Serializable;

public class Player implements Serializable {

    private String playerName;
    private Player opponent;
    private int playerRoundScore;
    private int playerTotalScore;
    private boolean isReadyToPlay;


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void addRoundPoint(){
        this.playerRoundScore = playerRoundScore + 1;
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

    public boolean isReadyToPlay() {
        return isReadyToPlay;
    }

    public void setReadyToPlay(boolean readyToPlay) {
        isReadyToPlay = readyToPlay;
    }

}
