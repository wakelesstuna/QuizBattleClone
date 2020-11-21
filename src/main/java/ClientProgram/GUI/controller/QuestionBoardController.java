package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import Model.InfoObj;
import Model.Question;
import assets.IFxmlPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import serverProgram.STATE;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionBoardController implements Initializable, IFxmlPaths {

    ControllerUtil c = new ControllerUtil();

    private List<Button> answerButtonsList = new ArrayList<>();
    private Question question;
    List<String> testAnswerList = new ArrayList<>();
    List<String> testAnswerList2 = new ArrayList<>();

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

            // skicka till servern att man är redo for nästa runda
        }else {
            pressedbtn.setStyle("-fx-background-color: firebrick");
            // skicka till serven att man är redo för nästa runda
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
            c.changeScene(GAME_BOARD, questionField);
            GameBoardController.numberOfQuestions = 2; // sätt detta med properties
        } else {
            Main.currentQuestion++;
            Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SEND_QUESTION));
            //c.changeScene(QUESTION_BOARD, questionField);
            //AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
            //questionBoard.getChildren().setAll(pane);
            // här måste vi fråga servern om en ny fråga
            GameBoardController.numberOfQuestions--;

        }
    }


    public List<Question> makeTestQuestion(){
        Question question1 = new Question("Vem grundade Java?", "James Gosling", testAnswerList);
        Question question2 = new Question("Vad hette Java från början?", "Oak", testAnswerList2 );
        List<Question> tempList = new ArrayList<>();
        tempList.add(question1);
        tempList.add(question2);
        return tempList;

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
        answer = "Oak";
        testAnswerList2.add(answer);
        answer = "Latte";
        testAnswerList2.add(answer);
        answer = "Microsoft";
        testAnswerList2.add(answer);
        answer = "JavaScript";
        testAnswerList2.add(answer);

    }

    public Question checkRund(List<Question> list){

        if (Main.currentRound == 1 && Main.currentQuestion == 1)
            return list.get(0);
        else if (Main.currentRound == 1 && Main.currentQuestion == 2)
            return list.get(1);
        else
            return null;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        answerButtonsList.add(answer1);
        answerButtonsList.add(answer2);
        answerButtonsList.add(answer3);
        answerButtonsList.add(answer4);
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Label getCategoryLabel() {
        return categoryLabel;
    }

    public TextField getQuestionField() {
        return questionField;
    }

    public List<Button> getAnswerButtonsList() {
        return answerButtonsList;
    }
}
