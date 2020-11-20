package serverProgram;

import Model.Category;
import serverProgram.databas.Database;
import Model.Question;
import java.util.*;

/**
 * Den här klassen är bara Test class för DB
 */

public class Play {
    private List<Question> questions = new ArrayList<>();
    private int correctAnswerCount;

    public Play() {
        //Starta databas
        Database db = new Database();

        List<Category> categories = db.getCategories();

        System.out.println("Välj en kategori");
        System.out.println("--------------------------");
        for (Category category : categories) {
            System.out.println(category.getCategoryName());
        }

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try {
                String userChoiceCategory = in.nextLine();
                //Hämta ut frågor från valda kategori
                questions = db.getQuestionList(userChoiceCategory);
                showNextQuestion();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void showNextQuestion() {

        if (questions.size() != 0) {

            Question quiz = questions.get(0);

            System.out.println("--------------------------");
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
                    System.out.println("Rättsvar är: " + quiz.getCollectAnswer());
                }
                //Ta bort frågan från lista
                questions.remove(0);
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