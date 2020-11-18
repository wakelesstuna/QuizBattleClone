package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();


    @FXML
    private TextField userName;

    @FXML
    private AnchorPane login;

    public void loadMainGame(ActionEvent event) {
        // för att sätt spelaren namn till en static variabel
        if (userName.getText().isEmpty()){
            userName.setPromptText("You need to enter a name");
        }else {
            Main.playerName = userName.getText();
            AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
            login.getChildren().setAll(pane);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setPromptText("Playername");
    }
}
