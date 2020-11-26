package serverProgram.questionDAO;

import model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TriviaDB {

    private final String sportsUrlPath = "https://opentdb.com/api.php?amount=20&category=21&difficulty=medium&type=multiple";
    private final String scienceAndNatureUrlPath = "https://opentdb.com/api.php?amount=20&category=17&difficulty=medium&type=multiple";
    private final String animalsUrlPath = "https://opentdb.com/api.php?amount=20&category=27&difficulty=medium&type=multiple";
    private final String geographyUrlPath = "https://opentdb.com/api.php?amount=20&category=22&difficulty=medium&type=multiple";
    private final String historyUrlPath = "https://opentdb.com/api.php?amount=20&category=23&difficulty=medium&type=multiple";

    private final List<Question> sports = questionListFactory(sportsUrlPath);
    private final List<Question> scienceAndNature = questionListFactory(scienceAndNatureUrlPath);
    private final List<Question> animals = questionListFactory(animalsUrlPath);
    private final List<Question> geography = questionListFactory(geographyUrlPath);
    private final List<Question> history = questionListFactory(historyUrlPath);

    public List<Question> questionListFactory(String urlPath) {

        List<Question> questions = new ArrayList<>();

        try {
            URL url = new URL(urlPath);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String input;
            while ((input = in.readLine()) != null) {
                String inputTrim = input.substring(19);

                String category = null;
                String question = null;
                String correctAnswer = null;
                String wrongAnswerOne = null;
                String wrongAnswerTwo = null;
                String wrongAnswerThree;

                Pattern pattern = Pattern.compile("\"([^\"]*)\"");
                Matcher matcher = pattern.matcher(inputTrim);

                int counter = 0;
                while (matcher.find()) {

                    if (counter == 2) {
                        category = matcher.group(1).replaceAll("&quot;", "\"");
                    } else if (counter == 8) {
                        question = matcher.group(1).replaceAll("&quot;", "\"");
                    } else if (counter == 10) {
                        correctAnswer = matcher.group(1).replaceAll("&quot;", "\"");
                    } else if (counter == 12) {
                        wrongAnswerOne = matcher.group(1).replaceAll("&quot;", "\"");
                    } else if (counter == 13) {
                        wrongAnswerTwo = matcher.group(1).replaceAll("&quot;", "\"");
                    } else if (counter == 14) {
                        wrongAnswerThree = matcher.group(1).replaceAll("&quot;", "\"");
                        List<String> tempList = new ArrayList<>();
                        tempList.add(correctAnswer);
                        tempList.add(wrongAnswerOne);
                        tempList.add(wrongAnswerTwo);
                        tempList.add(wrongAnswerThree);

                        Collections.shuffle(tempList);

                        Question q = new Question(question, correctAnswer, tempList, category);
                        questions.add(q);
                        counter = 0;
                    }
                    counter++;
                }

                testOutPut(questions, 5);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;

    }

    public void testOutPut(List<Question> questions, int indexOfQuestion){
        System.out.println();
        System.out.println("Category: \n" + questions.get(indexOfQuestion).getCategoryName());
        System.out.println("Question: \n" + questions.get(indexOfQuestion).getQuestion());
        System.out.println("Correct answer: \n" + questions.get(indexOfQuestion).getCorrectAnswer());
        System.out.println("AnswerList: ");
        for (String s : questions.get(indexOfQuestion).getAnswerChoices()) {
            System.out.println(s);
        }
    }

    public List<Question> getSports(){
        return sports;
    }


    public List<Question> getScienceAndNature() {
        return scienceAndNature;
    }

    public List<Question> getAnimals() {
        return animals;
    }

    public List<Question> getGeography() {
        return geography;
    }

    public List<Question> getHistory() {
        return history;
    }

    public static void main(String[] args) {
        new TriviaDB();
    }


}
