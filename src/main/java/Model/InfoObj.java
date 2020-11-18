package Model;

import serverProgram.STATE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class InfoObj implements Serializable {

    private STATE state;
    private String name;
    private Category category;
    private int roundsPerGame;
    private Question question;

    public InfoObj(STATE state) {
        this.state = state;
    }

    // används när vi vill skicka playername till servern
    public InfoObj(STATE state, String name) {
        this.state = state;
        this.name = name;
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

    public STATE getState() {
        return state;
    }

    public String getName() {
        return name;
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
