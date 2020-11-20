package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import assets.IFxmlPaths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuController implements Initializable, IFxmlPaths {

    // används för att kunna byta scene
    ControllerUtil c = new ControllerUtil();

    @FXML
    private Button gologin;

    @FXML
    private Label userNameLabel;

    @FXML
    private AnchorPane gameMenu;

    // loading the selectPlayerScreen
    public void loadSelectPlayer(){
        c.changeScene(FxmlPaths.RANDOM_PLAYER.toString(), userNameLabel);
    }

    // to logout to loginScreen
    public void logout(ActionEvent event) {
        c.changeScene(LOGIN_MENU, userNameLabel);
    }

    // to set username to the input of the username
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameLabel.setText(Main.playerName);
    }
}

