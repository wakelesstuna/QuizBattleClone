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

        Button pressed = (Button) ae.getSource();

        if (pressed.getText().equals(question.getCollectAnswer())){
            pressed.setStyle("-fx-background-color: greenyellow");
            Main.playerConnection.sendObjectToServer(new InfoObj(STATE.HANDLE_ANSWER, pressed.getText()));

        }else {
            pressed.setStyle("-fx-background-color: firebrick");
            Main.playerConnection.sendObjectToServer(new InfoObj(STATE.HANDLE_ANSWER, pressed.getText()));
        }

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
