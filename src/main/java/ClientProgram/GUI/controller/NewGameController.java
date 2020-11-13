package ClientProgram.GUI.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class NewGameController {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private AnchorPane newGame;

    public void loadMainGame() {
        AnchorPane pane = c.loadFMXLFiles(currentClass, "mainGame");
        newGame.getChildren().setAll(pane);
    }

    public void loadNewGame(){
        AnchorPane pane = c.loadFMXLFiles(currentClass, "game");
        newGame.getChildren().setAll(pane);
    }
}
