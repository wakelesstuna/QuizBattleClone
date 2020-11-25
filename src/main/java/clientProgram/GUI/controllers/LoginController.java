package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtilImp;
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
    public void loadMainGame(ActionEvent event) {
        // A player needs to have a name
        if (userName.getText().isEmpty()){
            userName.setPromptText("You need to enter a name");
        }else {
            // Need to set all variables before loading the scene
            FxmlUtilImp.getGameBoardController().getYouName().setText(userName.getText());
            FxmlUtilImp.getGameMenuController().getUserNameLabel().setText(userName.getText());
            FxmlUtilImp.changeScenes(FxmlUtilImp.getGameMenuScene());

        }
    }


}
