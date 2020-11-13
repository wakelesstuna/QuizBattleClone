package ClientProgram.GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();


    @FXML
    private TextField userName;

    @FXML
    private AnchorPane login;

    public void loadMainGame(ActionEvent event) {
        AnchorPane pane = c.loadFMXLFiles(currentClass, "mainGame");
        login.getChildren().setAll(pane);
    }

}
