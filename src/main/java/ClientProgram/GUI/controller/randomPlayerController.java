package ClientProgram.GUI.controller;



import ClientProgram.Client;
import ClientProgram.GUI.ControllerUtil;
import ClientProgram.PlayerConnection;
import assets.IFxmlPaths;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class randomPlayerController implements Initializable, IFxmlPaths {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();
    int test = 1;

    @FXML
    private AnchorPane searchingForPlayers;

    @FXML
    public Button findPlayerButton;


    public void findPlayer() {
        c.changeScene(GAME_BOARD, findPlayerButton);
        //AnchorPane pane = c.loadFMXLFiles(currentClass, "gameBoard");
        //searchingForPlayers.getChildren().setAll(pane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // start lyssnar metod från server så man kan gå vidare när den returnar true
    }
}
