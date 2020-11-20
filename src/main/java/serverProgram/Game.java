package serverProgram;

import Model.Category;
import Model.Player;
import Model.Question;

import java.util.ArrayList;

public class Game {

    private Player currentPlayer;
    private Player notCurrentPlayer;
    private Player player1;
    private Player player2;
    private Category currentCategory;
    private ArrayList<Question> questionList;

    public void checkObjStatus(Player player, Object objFromPlayer) {
    }

    public void addPlayerToList(Player player) {

    }

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

    // för att ändra tur när man ska välja category
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

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
        //questionList = databas.getCurrentCategoryQuestionList(currentCategory);
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

}

