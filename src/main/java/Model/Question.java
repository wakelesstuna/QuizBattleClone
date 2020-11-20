package Model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class Question implements Serializable {
    private String question;
    private String collectAnswer;
    private List<String> answerChoices;

    public Question(String question, String collectAnswer, List<String> answerChoices) {
        this.question = question;
        this.collectAnswer = collectAnswer;
        Collections.shuffle(answerChoices);
        this.answerChoices = answerChoices;
    }

    public String getCollectAnswer() {
        return collectAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public boolean checkAnswer(String userChoice) {
        if(collectAnswer.equals(userChoice)) {
            return true;
        }
        return false;
    }
}

