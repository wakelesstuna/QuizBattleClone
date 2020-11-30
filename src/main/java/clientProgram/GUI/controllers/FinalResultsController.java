package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import model.FxmlPathsImp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.security.Signature;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FinalResultsController implements FxmlPathsImp {


    GameBoardController GBC = FxmlUtil.getGameBoardController();
    CategoryChoiceBoardController CCBC = FxmlUtil.getCategoryChoiceBoardController();
    QuestionBoardController QBC = FxmlUtil.getQuestionBoardController();

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
        GBC.getOpponentRound1Score().setText("0");
        GBC.getOpponentRound2Score().setText("0");
        GBC.getOpponentRound3Score().setText("0");
        GBC.getOpponentRound4Score().setText("0");
        GBC.getOpponentRound5Score().setText("0");
        GBC.getOpponentTotalScore().setText("0");

        GBC.getYouRound1Score().setText("0");
        GBC.getYouRound2Score().setText("0");
        GBC.getYouRound3Score().setText("0");
        GBC.getYouRound4Score().setText("0");
        GBC.getYouRound5Score().setText("0");
        GBC.getYourTotalScore().setText("0");

        GBC.getWhichRoundLabel().setText("0");
        GBC.setWhichRound(1);

        CCBC.getHistory().setVisible(true);
        CCBC.getScienceAndNature().setVisible(true);
        CCBC.getSports().setVisible(true);
        CCBC.getAnimals().setVisible(true);
        CCBC.getGeography().setVisible(true);

        QBC.setRounds(0);
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