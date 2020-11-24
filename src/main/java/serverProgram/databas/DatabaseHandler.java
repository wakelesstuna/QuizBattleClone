package serverProgram.databas;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Question;


public class DatabaseHandler {

    //Läser in alla frågor och skapar Question objekt
    public static List<Question> readDataFromFile(Path filePath, String categoryName) {
        String question, correctAnswer;
        List<Question> questionList = new ArrayList<>();

        try (Scanner in = new Scanner(filePath)) {
            while (in.hasNext()) {
                List<String> answerChoices = new ArrayList<>();
                question = in.nextLine();
                correctAnswer = in.nextLine();
                answerChoices.add(correctAnswer);
                answerChoices.add(in.nextLine());
                answerChoices.add(in.nextLine());
                answerChoices.add(in.nextLine());

                Question q = new Question(question, correctAnswer, answerChoices, categoryName);
                questionList.add(q);
            }
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa in");
            e.printStackTrace();
            System.exit(0);
        }

        return questionList;
    }

}
