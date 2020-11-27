package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField userName;

    @FXML
    public Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setPromptText("Player name");
    }

    @FXML
    public void loadMainGame() {
        if (userName.getText().isEmpty()){
            userName.setPromptText("You need to enter a name");
        }else {
            FxmlUtil.getGameBoardController().getYouName().setText(userName.getText());
            FxmlUtil.getGameMenuController().getUserNameLabel().setText(userName.getText());
            Main.playerName = userName.getText();
            FxmlUtil.changeScenes(FxmlUtil.getGameMenuScene());

        }
    }


}
