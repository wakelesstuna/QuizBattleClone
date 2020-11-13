package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class selectPlayerController {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private AnchorPane newGame;


    public void loadMainGame() {
        AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
        newGame.getChildren().setAll(pane);
    }

    public void loadSearchForPlayers(){
        AnchorPane pane = c.loadFMXLFiles(currentClass, "searchingForPlayer");
        newGame.getChildren().setAll(pane);
        // skicka till servern att man är i status att leta spelare att spela mot
    }
}
