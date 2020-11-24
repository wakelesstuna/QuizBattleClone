package Model;

import java.io.Serializable;

public class StartPackage implements Serializable {

    // Send game settings to Client
    private int gameRounds;
    private int questionPerRounds;

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
