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
import javafx.scene.control.ProgressIndicator;
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

    @FXML
    private ProgressIndicator waitingInicator;

    @FXML
    private Label waitingLabel;

    private int rounds = 0;


    //för att skicka den knapp man har tryckt på till server
    public void answerButton(ActionEvent ae){

        for (Button b : answerButtonsList){
            b.setDisable(true);
        }

        Button pressed = (Button) ae.getSource();

        if (pressed.getText().equals(question.getCollectAnswer())){
            pressed.setStyle("-fx-background-color: greenyellow");
            ControllerUtil.getGameBoardController().getYourTotalScore().setText(String.valueOf(Integer.parseInt(ControllerUtil.getGameBoardController().getYourTotalScore().getText()) + 1));
            setRoundScore();
        }else {
            pressed.setStyle("-fx-background-color: firebrick");

        }
        waitingInicator.setVisible(true);
        waitingLabel.setVisible(true);


        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.HANDLE_ANSWER, pressed.getText()));

    }

    public void setRoundScore(){
        if (rounds == 1){
            ControllerUtil.getGameBoardController().getYouRound1Score().setText(String.valueOf(Integer.parseInt(ControllerUtil.getGameBoardController().getYouRound1Score().getText()) + 1));
        }else if (rounds == 2){
            ControllerUtil.getGameBoardController().getYouRound2Score().setText(String.valueOf(Integer.parseInt(ControllerUtil.getGameBoardController().getYouRound2Score().getText()) + 1));
        }else if (rounds == 3){
            ControllerUtil.getGameBoardController().getYouRound3Score().setText(String.valueOf(Integer.parseInt(ControllerUtil.getGameBoardController().getYouRound3Score().getText()) + 1));
        }else if (rounds == 4){
            ControllerUtil.getGameBoardController().getYouRound4Score().setText(String.valueOf(Integer.parseInt(ControllerUtil.getGameBoardController().getYouRound4Score().getText()) + 1));
        }else{
            ControllerUtil.getGameBoardController().getYouRound5Score().setText(String.valueOf(Integer.parseInt(ControllerUtil.getGameBoardController().getYouRound5Score().getText()) + 1));
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

    public void addRound(){
        this.rounds++;
    }

    public Question getQuestion() {
        return question;
    }

    public ProgressIndicator getWaitingInicator() {
        return waitingInicator;
    }

    public Label getWaitingLabel() {
        return waitingLabel;
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
