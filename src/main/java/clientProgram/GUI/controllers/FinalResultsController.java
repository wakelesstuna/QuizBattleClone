package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import model.FxmlPathsImp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class FinalResultsController implements FxmlPathsImp {

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
        resetGUIScores();
        try {
            Main.playerListener.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FxmlUtil.changeScenes(FxmlUtil.getGameMenuScene());
    }

    public void resetGUIScores() {
        FxmlUtil.getGameBoardController().getOpponentRound1Score().setText("0");
        FxmlUtil.getGameBoardController().getOpponentRound2Score().setText("0");
        FxmlUtil.getGameBoardController().getOpponentRound3Score().setText("0");
        FxmlUtil.getGameBoardController().getOpponentRound4Score().setText("0");
        FxmlUtil.getGameBoardController().getOpponentRound5Score().setText("0");
        FxmlUtil.getGameBoardController().getOpponentTotalScore().setText("0");

        FxmlUtil.getGameBoardController().getYouRound1Score().setText("0");
        FxmlUtil.getGameBoardController().getYouRound2Score().setText("0");
        FxmlUtil.getGameBoardController().getYouRound3Score().setText("0");
        FxmlUtil.getGameBoardController().getYouRound4Score().setText("0");
        FxmlUtil.getGameBoardController().getYouRound5Score().setText("0");
        FxmlUtil.getGameBoardController().getYourTotalScore().setText("0");

        FxmlUtil.getGameBoardController().getWhichRoundLabel().setText("0");
        FxmlUtil.getGameBoardController().setWhichRound(1);
        FxmlUtil.getQuestionBoardController().setRounds(0);
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
