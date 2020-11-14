package ClientProgram.GUI.controller;



import ClientProgram.GUI.ControllerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchingForPlayerController implements Initializable {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private AnchorPane searchingForPlayers;


    public void findPlayer() {
        AnchorPane pane = c.loadFMXLFiles(currentClass, "gameBoard");
        searchingForPlayers.getChildren().setAll(pane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // start lyssnar metod från server så man kan gå vidare när den returnar true
    }
}
