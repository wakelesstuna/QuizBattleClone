package clientProgram.GUI.controllers;


import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.InfoObj;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.STATE;

public class GameBoardController {

    private int numberOfRounds;
    private int numberOfQuestions;

    private int whichRoundNumber = 1;

    @FXML
    public Button playButton;

    @FXML
    public Button endGame;

    @FXML
    private Label whichRoundNumberLabel;

    @FXML
    private Label youName;

    @FXML
    private Label yourTotalScore;

    @FXML
    private Label opponentName;

    @FXML
    private Label opponentTotalScore;

    @FXML
    private Label youRound1Score;

    @FXML
    private Label youRound2Score;

    @FXML
    private Label youRound3Score;

    @FXML
    private Label youRound4Score;

    @FXML
    private Label youRound5Score;

    @FXML
    private Label opponentRound1Score;

    @FXML
    private Label opponentRound2Score;

    @FXML
    private Label opponentRound3Score;

    @FXML
    private Label opponentRound4Score;

    @FXML
    private Label opponentRound5Score;

    @FXML
    private Pane twoRoundPane;

    @FXML
    private Pane threeRoundPane;

    @FXML
    private Pane fourRoundPane;

    public void loadQuestion(){
        FxmlUtil.getQuestionBoardController().addRound();
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SEND_QUESTION, "java"));
        addRoundToGame();
        playButton.setDisable(true);
    }

    private void addRoundToGame(){
        this.whichRoundNumber += 1;
    }

    public void endGame(){
        FxmlUtil.changeScenes(FxmlUtil.getGameMenuScene());
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Pane getTwoRoundPane() {
        return twoRoundPane;
    }

    public Pane getThreeRoundPane() {
        return threeRoundPane;
    }

    public Pane getFourRoundPane() {
        return fourRoundPane;
    }

    public Label getYouName() {
        return youName;
    }

    public Label getOpponentName() {
        return opponentName;
    }

    public Label getWithRoundNumberLabel() {
        return whichRoundNumberLabel;
    }

    public int getWhichRoundNumber() {
        return whichRoundNumber;
    }

    public Label getYourTotalScore() {
        return yourTotalScore;
    }

    public Label getOpponentTotalScore() {
        return opponentTotalScore;
    }

    public Label getYouRound1Score() {
        return youRound1Score;
    }

    public Label getYouRound2Score() {
        return youRound2Score;
    }

    public Label getYouRound3Score() {
        return youRound3Score;
    }

    public Label getYouRound4Score() {
        return youRound4Score;
    }

    public Label getYouRound5Score() {
        return youRound5Score;
    }

    public Label getOpponentRound1Score() {
        return opponentRound1Score;
    }

    public Label getOpponentRound2Score() {
        return opponentRound2Score;
    }

    public Label getOpponentRound3Score() {
        return opponentRound3Score;
    }

    public Label getOpponentRound4Score() {
        return opponentRound4Score;
    }

    public Label getOpponentRound5Score() {
        return opponentRound5Score;
    }
}
