package serverProgram;

import serverProgram.databas.Question;
import serverProgram.databas.databas;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Toshiko Kuno
 * Date: 2020-11-13
 * Time: 16:18
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Play {
    private List<Question> questionList;
    private int correctAnswerCount;

    public Play() {
        System.out.println("Välj ett kategori");
        System.out.println("1. java");
        System.out.println("2. geografi");
        System.out.println("3. litteratur");
        System.out.println("4. musik");
        System.out.println("5. sport");

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try {
                int userChoice = in.nextInt();
                String userChoiceCategory = getCategory(userChoice);
                startGame(userChoiceCategory);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String getCategory(int userChoice) {
        String userChoiceCategory;
        switch (userChoice) {
            case 1:
                userChoiceCategory = "java";
                break;
            case 2:
                userChoiceCategory = "geografi";
                break;
            case 3:
                userChoiceCategory = "litteratur";
                break;
            case 4:
                userChoiceCategory = "musik";
                break;
            case 5:
                userChoiceCategory = "sport";
                break;
            default:
                userChoiceCategory = null;
                break;
        }
        return userChoiceCategory;
    }

    public void startGame(String category) {
        databas db = new databas();
        questionList = db.getQuestionsList(category);
        showNextQuestion();
    }


    public void showNextQuestion() {

        if (questionList.size() != 0) {

            //Hämta en slumpa fråga från kategoriet
            Random random = new Random();
            int randomNum = random.nextInt(questionList.size());
            Question quiz = questionList.get(randomNum);

            //Shuffle choices
            quiz.shuffleAnswerChoices();

            System.out.println("Fråga: " + quiz.getQuestion());
            System.out.println("Svaralternativer: " + quiz.getAnswerChoices());

            System.out.println("Välj ett svar");
            Scanner in = new Scanner(System.in);
            while (in.hasNext()) {
                if (quiz.checkAnswer(in.nextLine())) {
                    System.out.println("Rätt!!");
                    correctAnswerCount++;
                } else {
                    System.out.println("FEEL!!");
                }
                //Ta bort frågan från lista
                questionList.remove(randomNum);
                //Visa en ny fråga
                showNextQuestion();
            }
        } else { //Om det inte finns fråga då kommer hit
            System.out.println("--------------------------");
            System.out.println("Finish game!!");
            System.out.println("Du fick " + correctAnswerCount + " poäng");
        }


    }

    public static void main(String[] args) {
        new Play();
    }
}
