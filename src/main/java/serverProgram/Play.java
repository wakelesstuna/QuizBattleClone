package serverProgram;

import serverProgram.databas.Question;
import serverProgram.databas.databas;

import java.util.List;
import java.util.Random;

/**
 * Created by Toshiko Kuno
 * Date: 2020-11-13
 * Time: 16:18
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Play {
    private List<Question> questionList;

    public Play() {
        databas db = new databas();
        questionList = db.getQuestionsList("java");
        showNextQuestion();
    }


    public void showNextQuestion() {

        //Hämta en slumpa fråga från kategoriet
        Random random = new Random();
        int randomNum = random.nextInt(questionList.size());
        Question quiz = questionList.get(randomNum);

        //Shuffle choices
        quiz.shuffleAnswerChoices();

        System.out.println("--------Visa fråga---------");
        System.out.println("Fråga: " + quiz.getQuestion());
        System.out.println("Svar: " + quiz.getAnswerChoices());
        System.out.println("Rätt svar: " + quiz.getCollectAnswer());

        String choice = quiz.getAnswerChoices().get(0);
        System.out.println(quiz.checkAnswer(choice));

        //Ta bort frågan från lista
        questionList.remove(randomNum);
    }

    public static void main(String[] args) {
        new Play();
    }
}
