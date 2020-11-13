package serverProgram.databas;

import java.util.List;
import java.util.Random;

public class databas {
    private List<Question> questionList;

    public databas(String category) {
       questionList = IUtil.readDataFromFile(category);
        showNextQuestion();
        showNextQuestion();
    }

    public void showNextQuestion() {
        Random random = new Random();
        int randomNum = random.nextInt(questionList.size());

        //Hämta en fråga
        Question quiz = questionList.get(randomNum);

        System.out.println("--------Visa fråga---------");
        System.out.println("Fråga: " + quiz.getQuestion());
        quiz.shuffleAnswerChoices();
        System.out.println("Svar: " + quiz.getAnswerChoices());
        System.out.println("Rätt svar: " + quiz.getCollectAnswer());

        //Ta bort frågan från lista
        questionList.remove(randomNum);

    }

    public static void main(String[] args) {
        String category = "java";
        new databas(category);
    }
}
