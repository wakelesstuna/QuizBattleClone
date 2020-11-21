package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // LoginMenu

    @FXML
    private TextField userName;

    @FXML
    public Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setPromptText("Playername");
    }

    @FXML
    public void loadMainGame(ActionEvent event) {
        // A player needs to have a name
        if (userName.getText().isEmpty()){
            userName.setPromptText("You need to enter a name");
        }else {
            // Need to set all variables before loading the scene
            ControllerUtil.getGameBoardController().getYouName().setText(userName.getText());
            ControllerUtil.getGameMenuController().getUserNameLabel().setText(userName.getText());
            ControllerUtil.changeScenes(ControllerUtil.getGameMenuScene());

        }
    }


}
