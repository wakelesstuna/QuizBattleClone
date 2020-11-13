package ClientProgram.GUI.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainGameController {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private AnchorPane mainGame;

    @FXML
    private Label userNameLabel;

    @FXML
    private Button startNewGameBtn;

    public void loadNewGame(ActionEvent event) {
        AnchorPane pane = c.loadFMXLFiles(currentClass, "newGame");
        mainGame.getChildren().setAll(pane);
    }

    public void logout(){
        AnchorPane pane = c.loadFMXLFiles(currentClass, "login");
        mainGame.getChildren().setAll(pane);
    }

}
