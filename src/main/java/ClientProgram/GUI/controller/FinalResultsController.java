package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import assets.IFxmlPaths;
import javafx.beans.WeakInvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FinalResultsController implements Initializable, IFxmlPaths {



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

    public void loadGameMenu(){
        GameBoardController.currentRound = 1;
        GameBoardController.numberOfRounds = 2; // reset the round count with

       // c.changeScene(GAME_MENU, whoWinLabel);
        //AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
        //finalScore.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        youFinalScore.setText("" + GameBoardController.gameTotalScore);
        opponentFinalScore.setText("" + GameBoardController.opponentPoints);
        if (GameBoardController.gameTotalScore > GameBoardController.opponentPoints){
            whoWinLabel.setText("YOU WIN!");
            winnerMsgLabel.setText("CONGRATULATIONS!");
        }else if (GameBoardController.gameTotalScore == GameBoardController.opponentPoints){
            whoWinLabel.setText("IT'S A TIE");
            winnerMsgLabel.setText("BETTER LUCK\nNEXT TIME!");
        }else {
            whoWinLabel.setText("YOU LOSE!");
            winnerMsgLabel.setText("BETTER LUCK NEXT\nTIME!");
        }

    }
}
