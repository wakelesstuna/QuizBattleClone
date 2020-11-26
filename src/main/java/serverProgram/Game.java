package serverProgram;

import model.Player;

public class Game {

    private Player currentPlayer;
    private Player notCurrentPlayer;
    private Player player1 = new Player();
    private Player player2 = new Player();

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getNotCurrentPlayer() {
        return notCurrentPlayer;
    }

    public void setNotCurrentPlayer(Player notCurrentPlayer) {
        this.notCurrentPlayer = notCurrentPlayer;
    }

    public void switchCurrentPlayers(){
        Player temp = currentPlayer;
        currentPlayer = notCurrentPlayer;
        notCurrentPlayer = temp;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

}

