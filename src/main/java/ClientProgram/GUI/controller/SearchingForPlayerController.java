package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class SearchingForPlayerController {

    ControllerUtil c = new ControllerUtil();


    @FXML
    public Button findPlayerButton;


    // anropas när servern har skickat svar om att den hittat en spelare
    public void foundPlayer(){
        c.changeScene(FxmlPaths.GAME_BOARD.toString(), findPlayerButton);
    }

    public void findPlayer() {
        c.changeScene(FxmlPaths.GAME_BOARD.toString(), findPlayerButton);
    }


}
