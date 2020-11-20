package Model;

public class Player {

    private String name;
    private int playerRoundScore;
    private int playerTotalScore;
    private boolean isReadyToPlay;
    private boolean hasAnswered;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
