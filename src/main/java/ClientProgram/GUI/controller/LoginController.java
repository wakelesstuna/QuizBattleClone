package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    ControllerUtil c = new ControllerUtil();

    @FXML
    private TextField userName;

    @FXML
    private AnchorPane login;

    @FXML
    public Button loginButton;

    @FXML
    public void loadMainGame(ActionEvent event) {
        // för att sätt spelaren namn till en static variabel
        if (userName.getText().isEmpty()){
            userName.setPromptText("You need to enter a name");
        }else {
            Main.playerName = userName.getText();
            userName.setText("");
            c.changeScene(FxmlPaths.GAME_MENU.toString(), loginButton);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setPromptText("Playername");
    }
}
