package serverProgram;

import serverProgram.databas.Category;
import serverProgram.databas.Database;
import serverProgram.databas.Question;

import java.util.*;

public class Play {
    private List<Question> questions = new ArrayList<>();
    private int correctAnswerCount;

    public Play() {
        Database db = new Database();

        List<Category> categories = db.getCategories();
        List<String> categoriesList = new ArrayList<>();

        //Lägg alla kategorier i categoriesList
        for(Category category : categories) {
            categoriesList.add(category.getCategoryName());
        }

        //Shuffle categorier
        Collections.shuffle(categoriesList);

        //Hämta ut 3 slumpa kategorier
        System.out.println("Välj ett kategori");
        for (int i = 0; i < 3; i++) {
            System.out.println(categoriesList.get(i));
        }


        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try {
                String userChoice = in.nextLine();
                //Hämta ut frågor från valda kategori
                questions = db.getQuestions(userChoice);
                showNextQuestion();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void showNextQuestion() {

        if (questions.size() != 0) {

            //Hämta en slumpa fråga från kategoriet
            Random random = new Random();
            int randomNum = random.nextInt(questions.size());
            Question quiz = questions.get(randomNum);

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
                questions.remove(randomNum);
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
