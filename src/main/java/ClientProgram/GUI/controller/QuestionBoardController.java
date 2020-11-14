package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import Model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionBoardController implements Initializable {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    List<Button> answerButtonsList = new ArrayList<>();
    List<String> testAnswerList = new ArrayList<>();
    Question question;

    @FXML
    private AnchorPane questionBoard;

    @FXML
    private Label categoryLabel;

    @FXML
    private TextField questionField;

    @FXML
    private Button answer1;

    @FXML
    private Button answer2;

    @FXML
    private Button answer3;

    @FXML
    private Button answer4;


    //för att skicka den knapp man har tryckt på till server
    public void answerButton(ActionEvent ae){

        for (Button b : answerButtonsList){
            b.setDisable(true);
        }

        Button pressedbtn = (Button) ae.getSource();

        if (pressedbtn.getText().equals(question.getCollectAnswer())){
            pressedbtn.setStyle("-fx-background-color: greenyellow");

            // addar 1 poäng till din score
            GameBoardController.gameRoundScore = GameBoardController.gameRoundScore + 1;
            GameBoardController.gameTotalScore = GameBoardController.gameTotalScore + 1;
        }else {
            pressedbtn.setStyle("-fx-background-color: firebrick");
        }
        /*
        if (pressedbtn.getText().equals(question.getCorrectAnser)){
            pressedbtn.setStyle("-fx-background-color: greenyellow");

            // addar 1 poäng till din score
            GameBoardController.gameRoundScore = GameBoardController.gameRoundScore + 1;
            GameBoardController.gameTotalScore = GameBoardController.gameTotalScore + 1;
            // skicka dina poäng till server
            new Request(SEND_SCORE, gameBoardController.gameRoundScore);
        }else {
            pressedbtn.setStyle("-fx-background-color: firebrick");
        }
        */


        String answer = pressedbtn.getText();

        // Skicka svar till servern för att kolla om de är rätt
        //new Request(SEND_ANSWER, answer)
    }

    public void nextQuestion(){
        // om de finns en fråga till skicka den från servern
        if (GameBoardController.numberOfQuestions == 1) {
            // ladda en FinalScoreBoard med slutresultat
            AnchorPane pane = c.loadFMXLFiles(currentClass, "gameBoard");
            questionBoard.getChildren().setAll(pane);
            GameBoardController.numberOfQuestions = 2; // sätt detta med properties
        } else {
            AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
            questionBoard.getChildren().setAll(pane);
            // här måste vi fråga servern om en ny fråga
            GameBoardController.numberOfQuestions--;
        }
    }


    public void makeTestQuestion(){
        question = new Question("Vem grundade Java?", "James Gosling", testAnswerList);
    }

    public void testAnswerList(){
        String answer = "James Gosling";
        testAnswerList.add(answer);
        answer = "Oscar Forss";
        testAnswerList.add(answer);
        answer = "Mahmud";
        testAnswerList.add(answer);
        answer = "Sigrun";
        testAnswerList.add(answer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryLabel.setText(categoryLabel.getText() + Main.categoryName);
        testAnswerList();
        makeTestQuestion();

        // här skickas frågan från servern
        // man plockar ut frågan och sätter den till questionfield
        // sen sätter man svaren till knapparna

        questionField.setText(question.getQuestion());

        answerButtonsList.add(answer1);
        answerButtonsList.add(answer2);
        answerButtonsList.add(answer3);
        answerButtonsList.add(answer4);

        testAnswerList();

        for (int i = 0; i < answerButtonsList.size(); i++) {
            answerButtonsList.get(i).setText(testAnswerList.get(i));
        }

        // TODO: 2020-11-13 Detta funkar super bra :D

    }
}
