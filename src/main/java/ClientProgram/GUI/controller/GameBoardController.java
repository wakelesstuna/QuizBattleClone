package ClientProgram.GUI.controller;


import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import Model.InfoObj;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import serverProgram.STATE;

public class GameBoardController {


    public static String opponent = "opponent"; // hämta motståndarnamn från servern
    public static int opponentPoints = 1;       // hämta motståndarscore från servern

    public static int numberOfRounds = 2; // sätt dessa med proptiesfil
    public static int numberOfQuestions = 2; // sätt dessa med proptiesfil

    public static int currentRound = 1;
    public int gameRoundScore = 0;
    public static int gameTotalScore = 0;

    //public static Question currentQuestion; // här sätter vi frågan frpom servern

    @FXML
    public Button playButton;

    @FXML
    public Button endGame;

    @FXML
    private Label rounds;

    @FXML
    private Label youName;

    @FXML
    private Label opponentName;

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

    public void loadQuestion(){
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SEND_QUESTIONLIST, "java"));
        //ControllerUtil.changeScenes(ControllerUtil.getQuestionBoardScene());
    }

    public void endGame(){
        ControllerUtil.changeScenes(ControllerUtil.getGameMenuScene());
    }

    public Label getYouName() {
        return youName;
    }

    public Label getOpponentName() {
        return opponentName;
    }

    public static String getOpponent() {
        return opponent;
    }

    public static void setOpponent(String opponent) {
        GameBoardController.opponent = opponent;
    }

    public int getGameRoundScore() {
        return gameRoundScore;
    }

    public void setGameRoundScore(int gameRoundScore) {
        this.gameRoundScore = gameRoundScore;
    }

    public static int getGameTotalScore() {
        return gameTotalScore;
    }

    public static void setGameTotalScore(int gameTotalScore) {
        GameBoardController.gameTotalScore = gameTotalScore;
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
