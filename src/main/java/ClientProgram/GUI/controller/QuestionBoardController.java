package ClientProgram.GUI.controller;

import ClientProgram.Client;
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

    private List<Button> answerButtonsList = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();
    private Question question;

    private int roundsPerGame = 2;
    private int questionsPerRound = 2;

    private int currentRound = 0;
    private int currentQuestion = 0;

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
            ControllerUtil.getGameBoardController().setGameRoundScore(ControllerUtil.getGameBoardController().gameRoundScore + 1);

        }else {
            pressed.setStyle("-fx-background-color: firebrick");

        }

        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.HANDLE_ANSWER, pressed.getText()));

    }

    public void nextQuestion(){
        if (currentRound < roundsPerGame){
            currentQuestion++;
            question = questionList.get(currentQuestion);
            questionField.setText(question.getQuestion());

            for (int i = 0; i < answerButtonsList.size(); i++) {
                answerButtonsList.get(i).setDisable(false);
                answerButtonsList.get(i).setStyle("-fx-background-color: #D1FDFF");
                answerButtonsList.get(i).setText(questionList.get(currentQuestion).getAnswerChoices().get(i));
                currentRound++;
            }
            ControllerUtil.changeScenes(ControllerUtil.getQuestionBoardScene());
        }else{
            Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SEND_QUESTIONLIST, "geografi"));
            ControllerUtil.getGameBoardController().getYouRound1Score().setText(String.valueOf(ControllerUtil.getGameBoardController().gameRoundScore));
            ControllerUtil.changeScenes(ControllerUtil.getGameBoardScene());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        answerButtonsList.add(answer1);
        answerButtonsList.add(answer2);
        answerButtonsList.add(answer3);
        answerButtonsList.add(answer4);

        for (Button b : answerButtonsList) {
            b.setDisable(false);
        }
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

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public void setRoundsPerGame(int roundsPerGame) {
        this.roundsPerGame = roundsPerGame;
    }

    public int getQuestionsPerRound() {
        return questionsPerRound;
    }

    public void setQuestionsPerRound(int questionsPerRound) {
        this.questionsPerRound = questionsPerRound;
    }
}
