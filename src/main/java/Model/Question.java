package Model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class Question implements Serializable {

    private String question;
    private String collectAnswer;
    private List<String> answerChoices;
    private String categoryName;

    public Question(String question, String collectAnswer, List<String> answerChoices, String categoryName) {
        this.question = question;
        this.collectAnswer = collectAnswer;
        Collections.shuffle(answerChoices);
        this.answerChoices = answerChoices;
        this.categoryName = categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1).toLowerCase();
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

    public String getCategoryName() {
        return categoryName;
    }

    public boolean checkAnswer(String userChoice) {
        if(collectAnswer.equals(userChoice)) {
            return true;
        }
        return false;
    }
}

