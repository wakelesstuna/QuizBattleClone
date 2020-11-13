package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
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
        // för att sätt spelaren namn till en static variabel
        Main.playerName = userName.getText();
        AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
        login.getChildren().setAll(pane);
    }

}
