package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import model.FxmlPathsImp;
import model.InfoObj;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.STATE;

public class RandomPlayerController implements FxmlPathsImp {

    @FXML
    public Button randomPlayer;

    // to go back to mainGameScreen
    public void loadMainGame() {
        FxmlUtil.changeScenes(FxmlUtil.getGameMenuScene());
    }

    // Creates a connection to the server, sends userName to server, sends that this player is ready to play a game
    public void loadRandomPlayer() {
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_PLAYERNAME, FxmlUtil.getGameMenuController().getUserNameLabel().getText()));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.READY_TO_PLAY));
        FxmlUtil.changeScenes(FxmlUtil.getSearchingForPlayerScene());
    }
}
