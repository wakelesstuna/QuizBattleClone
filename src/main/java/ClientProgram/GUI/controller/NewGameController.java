package ClientProgram.GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NewGameController {

    @FXML
    private AnchorPane newGame;

    public void loadMainGame() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainGame.fxml"));
        newGame.getChildren().setAll(pane);
    }
}
