package model;

import java.io.Serializable;

public class InfoObj implements Serializable {

    private final STATE state;
    private String msg;
    private Player opponent;

    public InfoObj(STATE state) {
        this.state = state;
    }

    public InfoObj(STATE state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public InfoObj(STATE state, Player opponent) {
        this.state = state;
        this.opponent = opponent;
    }

    public STATE getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public Player getOpponent() {
        return opponent;
    }

}
