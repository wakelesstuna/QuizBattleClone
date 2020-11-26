package model;

import java.io.Serializable;

public class StartPackage implements Serializable {

    private final int gameRounds;
    private final int questionPerRounds;

    public StartPackage(int gameRounds, int questionPerRounds) {
        this.gameRounds = gameRounds;
        this.questionPerRounds = questionPerRounds;
    }

    public int getGameRounds() {
        return gameRounds;
    }

    public int getQuestionPerRounds() {
        return questionPerRounds;
    }
}
