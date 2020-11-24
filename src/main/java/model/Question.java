package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class Question implements Serializable {

    private final String categoryName;
    private final String question;
    private final String correctAnswer;
    private final List<String> answerChoices;


    public Question(String question, String correctAnswer, List<String> answerChoices, String categoryName) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        Collections.shuffle(answerChoices);
        this.answerChoices = answerChoices;
        this.categoryName = categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1).toLowerCase();
    }

    public String getCorrectAnswer() {
        return correctAnswer;
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

}

