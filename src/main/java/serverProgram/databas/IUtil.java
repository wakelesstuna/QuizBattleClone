package serverProgram.databas;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Toshiko Kuno
 * Date: 2020-11-13
 * Time: 12:46
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class IUtil {
    public static List<Question> readDataFromFile(Path filePath) {
        String question;
        String correctAnswer;
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

                Question q = new Question(question, correctAnswer, answerChoices);
               /* System.out.println("------Läsa in data--------");
                System.out.println(q.getQuestion());
                System.out.println(q.getCollectAnswer());
                System.out.println(q.getAnswerChoices());*/
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
