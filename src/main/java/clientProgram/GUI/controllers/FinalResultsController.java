package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import model.FxmlPathsImp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FinalResultsController implements FxmlPathsImp {


    @FXML
    private AnchorPane finalScore;

    @FXML
    private Label whoWinLabel;

    @FXML
    private Label youFinalScore;

    @FXML
    private Label opponentName;

    @FXML
    private Label opponentFinalScore;

    @FXML
    private Label winnerMsgLabel;

    public void loadGameMenu() {

        try {
            Main.playerConnection.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FxmlUtil.changeScenes(FxmlUtil.getGameMenuScene());
    }

    public Label getOpponentName() {
        return opponentName;
    }

    public Label getWhoWinLabel() {
        return whoWinLabel;
    }


    public Label getYouFinalScore() {
        return youFinalScore;
    }

    public Label getOpponentFinalScore() {
        return opponentFinalScore;
    }

    public Label getWinnerMsgLabel() {
        return winnerMsgLabel;
    }

}
