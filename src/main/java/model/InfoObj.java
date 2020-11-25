package model;

import java.io.Serializable;

public class InfoObj implements Serializable {

    private final STATE state;
    private String msg;
    private String SceneToChangeTo;
    private Player player;
    private Player opponent;
    private String answer;
    private Category category;
    private int roundsPerGame;
    private int roundScore;
    private Question question;

    public InfoObj(STATE state) {
        this.state = state;
    }

    // används när vi vill skicka playername till servern
    public InfoObj(STATE state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public InfoObj(STATE state, String SceneToChangeTo, Player player) {
        this.state = state;
        this.SceneToChangeTo = SceneToChangeTo;
        this.player = player;
    }

    public InfoObj(STATE state, String SceneToChangeTo, int roundScore) {
        this.state = state;
        this.SceneToChangeTo = SceneToChangeTo;
        this.roundScore = roundScore;
    }


    // när vi vill skicka antal rundor till spelare
    public InfoObj(STATE state, int roundsPerGame) {
        this.state = state;
        this.roundsPerGame = roundsPerGame;
    }

    // när vi vill skicka vilken category av frågor servern ska skicka till player
    public InfoObj(STATE state, Category category) {
        this.state = state;
        this.category = category;
    }

    // används för att skicka en fråga
    public InfoObj(STATE state, Question question) {
        this.state = state;
        this.question = question;
    }

    // fuling här för att kunna skicka en sträng med spelaren svar tills servern
    public InfoObj(STATE state, Question question, String answer) {
        this.state = state;
        this.question = question;
        this.answer = answer;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public STATE getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSceneToChangeTo() {
        return SceneToChangeTo;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return opponent;
    }

    public Category getCategory() {
        return category;
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public Question getQuestion() {
        return question;
    }
}
