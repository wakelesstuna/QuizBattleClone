package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;

import ClientProgram.GUI.Main;
import Model.InfoObj;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import serverProgram.STATE;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchingForPlayerController implements Initializable {

    ControllerUtil c = new ControllerUtil();
    SearchingForPlayerController searchingForPlayerController = SearchingForPlayerController.this;

    @FXML
    public Button findPlayerButton;

    public void testSendObj(){
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.READY_TO_PLAY, "ready to play"));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SEND_QUESTION, "send question"));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.GAME_OVER, "game over"));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.ASK_CATEGORY, "ASK_CATEGORY"));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "SET_CATEGORY"));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.HANDLE_ANSWER, "HANDLE_ANSWER"));


    }

    // anropas när servern har skickat svar om att den hittat en spelare
    public void foundPlayer(){
        c.changeScene(FxmlPaths.GAME_BOARD.toString(), findPlayerButton);
    }

    public void findPlayer() {
        c.changeScene(FxmlPaths.GAME_BOARD.toString(), findPlayerButton);
    }

    public SearchingForPlayerController getSearchingForPlayerController() {
        return searchingForPlayerController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // start lyssnar metod från server så man kan gå vidare när den returnar true
    }
}
