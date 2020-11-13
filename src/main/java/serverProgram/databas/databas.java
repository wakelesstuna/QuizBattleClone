package serverProgram.databas;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class databas {


    public static void main(String[] args) throws Exception{
    List<Question> questionList = new ArrayList<>();
        String category = "java";
        Path filePath = Paths.get("src/main/java/serverProgram/databas/"+ category  + ".txt");
        try (Scanner in = new Scanner(filePath)){
            while (in.hasNext()) {
                List<String> tempChoices = new ArrayList<>();
                String question = in.nextLine();
                String correctAnswer = in.nextLine();
                tempChoices.add(correctAnswer);
                tempChoices.add(in.nextLine());
                tempChoices.add(in.nextLine());
                tempChoices.add(in.nextLine());

                Question q = new Question(question, correctAnswer, tempChoices);
                System.out.println(q.getQuestion());
                System.out.println(q.getCollectAnswer());
                System.out.println(q.getAnswerChoices());
                questionList.add(q);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Det gick inte att läsa in");

        }

    }
}
