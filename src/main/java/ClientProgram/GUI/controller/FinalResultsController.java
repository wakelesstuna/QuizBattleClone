package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class FinalResultsController {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private AnchorPane finalScore;

    public void loadGameMenu(){
        GameBoardController.currentRound = 1;
        GameBoardController.numberOfRounds = 2; // reset the round count with
        AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
        finalScore.getChildren().setAll(pane);
    }
}
