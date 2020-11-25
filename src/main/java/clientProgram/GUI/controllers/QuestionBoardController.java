package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtilImp;
import clientProgram.Main;
import javafx.scene.control.*;
import model.InfoObj;
import model.Question;
import model.FxmlPathsImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.STATE;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionBoardController implements Initializable, FxmlPathsImp {

    private final List<Button> answerButtonsList = new ArrayList<>();
    private Question question;

    @FXML
    private Label categoryLabel;

    @FXML
    private TextArea questionField;

    @FXML
    private Button answer1;

    @FXML
    private Button answer2;

    @FXML
    private Button answer3;

    @FXML
    private Button answer4;

    @FXML
    private ProgressIndicator waitingIndicator;

    @FXML
    private Label waitingLabel;

    private int rounds = 0;

    //för att skicka den knapp man har tryckt på till server
    public void answerButton(ActionEvent ae){

        for (Button b : answerButtonsList){
            b.setDisable(true);
        }

        Button pressed = (Button) ae.getSource();

        if (pressed.getText().equals(question.getCorrectAnswer())){
            pressed.setStyle("-fx-background-color: greenyellow");
            FxmlUtilImp.getGameBoardController().getYourTotalScore().setText(String.valueOf(Integer.parseInt(FxmlUtilImp.getGameBoardController().getYourTotalScore().getText()) + 1));
            setRoundScore();
        }else {
            pressed.setStyle("-fx-background-color: firebrick");

        }
        waitingIndicator.setVisible(true);
        waitingLabel.setVisible(true);


        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.HANDLE_ANSWER, pressed.getText()));

    }

    public void setRoundScore(){
        if (rounds == 1){
            FxmlUtilImp.getGameBoardController().getYouRound1Score().setText(String.valueOf(Integer.parseInt(FxmlUtilImp.getGameBoardController().getYouRound1Score().getText()) + 1));
        }else if (rounds == 2){
            FxmlUtilImp.getGameBoardController().getYouRound2Score().setText(String.valueOf(Integer.parseInt(FxmlUtilImp.getGameBoardController().getYouRound2Score().getText()) + 1));
        }else if (rounds == 3){
            FxmlUtilImp.getGameBoardController().getYouRound3Score().setText(String.valueOf(Integer.parseInt(FxmlUtilImp.getGameBoardController().getYouRound3Score().getText()) + 1));
        }else if (rounds == 4){
            FxmlUtilImp.getGameBoardController().getYouRound4Score().setText(String.valueOf(Integer.parseInt(FxmlUtilImp.getGameBoardController().getYouRound4Score().getText()) + 1));
        }else{
            FxmlUtilImp.getGameBoardController().getYouRound5Score().setText(String.valueOf(Integer.parseInt(FxmlUtilImp.getGameBoardController().getYouRound5Score().getText()) + 1));
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

    public int getRounds() {
        return rounds;
    }

    public void addRound(){
        this.rounds++;
    }

    public Question getQuestion() {
        return question;
    }

    public ProgressIndicator getWaitingIndicator() {
        return waitingIndicator;
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

    public TextArea getQuestionField() {
        return questionField;
    }

    public List<Button> getAnswerButtonsList() {
        return answerButtonsList;
    }

}
