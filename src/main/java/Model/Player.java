package Model;

import java.io.ObjectOutputStream;

public class Player {

    private String playerName;
    private int playerRoundScore;
    private int playerTotalScore;
    private boolean isReadyToPlay;
    private boolean hasAnswered;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
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

    public boolean isHasAnswered() {
        return hasAnswered;
    }

    public void setHasAnswered(boolean hasAnswered) {
        this.hasAnswered = hasAnswered;
    }


}
