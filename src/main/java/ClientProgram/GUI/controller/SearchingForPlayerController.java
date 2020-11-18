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
    int test = 1;

    @FXML
    private AnchorPane searchingForPlayers;


    public void findPlayer() {
        AnchorPane pane = c.loadFMXLFiles(currentClass, "gameBoard");
        searchingForPlayers.getChildren().setAll(pane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (test == 1){

        }
        // start lyssnar metod från server så man kan gå vidare när den returnar true
    }
}
