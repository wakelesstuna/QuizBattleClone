package serverProgram.databas;

import java.util.Collections;
import java.util.List;

/**
 * Created by Toshiko Kuno
 * Date: 2020-11-13
 * Time: 10:12
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Question {
    private String question;
    private String collectAnswer;
    private List<String> answerChoices;

    public Question(String question, String collectAnswer, List<String> answerChoices) {
        this.question = question;
        this.collectAnswer = collectAnswer;
        this.answerChoices = answerChoices;
    }

    public String getCollectAnswer() {
        return collectAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void shuffleAnswerChoices() {
        Collections.shuffle(answerChoices);
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }
}
