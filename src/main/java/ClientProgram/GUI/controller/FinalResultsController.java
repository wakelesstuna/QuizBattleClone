package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import ClientProgram.PlayerConnection;
import assets.IFxmlPaths;
import javafx.beans.WeakInvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinalResultsController implements IFxmlPaths {


    @FXML
    private AnchorPane finalScore;

    @FXML
    private Label whoWinLabel;

    @FXML
    private Label youFinalScore;

    @FXML
    private Label opponentFinalScore;

    @FXML
    private Label winnerMsgLabel;

    public void loadGameMenu() {
        GameBoardController.currentRound = 1;
        GameBoardController.numberOfRounds = 2; // reset the round count with

        try {
            Main.playerConnection.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerUtil.changeScenes(ControllerUtil.getGameMenuScene());
    }


    public AnchorPane getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(AnchorPane finalScore) {
        this.finalScore = finalScore;
    }

    public Label getWhoWinLabel() {
        return whoWinLabel;
    }

    public void setWhoWinLabel(Label whoWinLabel) {
        this.whoWinLabel = whoWinLabel;
    }

    public Label getYouFinalScore() {
        return youFinalScore;
    }

    public void setYouFinalScore(Label youFinalScore) {
        this.youFinalScore = youFinalScore;
    }

    public Label getOpponentFinalScore() {
        return opponentFinalScore;
    }

    public void setOpponentFinalScore(Label opponentFinalScore) {
        this.opponentFinalScore = opponentFinalScore;
    }

    public Label getWinnerMsgLabel() {
        return winnerMsgLabel;
    }

    public void setWinnerMsgLabel(Label winnerMsgLabel) {
        this.winnerMsgLabel = winnerMsgLabel;
    }
}
