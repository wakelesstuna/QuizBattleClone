package ClientProgram.GUI.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainGameController {

    @FXML
    private AnchorPane mainGame;

    @FXML
    private Button startNewGameBtn;

    public void loadNewGame(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/newGame.fxml"));
        mainGame.getChildren().setAll(pane);
    }

}
