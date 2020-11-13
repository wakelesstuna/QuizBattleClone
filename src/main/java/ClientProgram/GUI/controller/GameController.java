package ClientProgram.GUI.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameController {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private Button gologin;

    @FXML
    private AnchorPane gamePane;

    public void goLogin(){
        AnchorPane pane = c.loadFMXLFiles(currentClass, "login");
        gamePane.getChildren().setAll(pane);

    }

}

