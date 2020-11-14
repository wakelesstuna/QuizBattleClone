package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuController implements Initializable {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private Button gologin;

    @FXML
    private Label userNameLabel;

    @FXML
    private AnchorPane gameMenu;

    public void loadNewGame(){
        AnchorPane pane = c.loadFMXLFiles(currentClass, "selectPlayer");
        gameMenu.getChildren().setAll(pane);

    }

    public void logout(ActionEvent event) {
        AnchorPane pane = c.loadFMXLFiles(currentClass, "loginMenu");
        gameMenu.getChildren().setAll(pane);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameLabel.setText(Main.playerName);
    }
}

