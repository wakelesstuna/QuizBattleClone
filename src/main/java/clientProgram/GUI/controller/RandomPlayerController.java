package clientProgram.GUI.controller;

import clientProgram.GUI.ControllerUtil;
import clientProgram.GUI.Main;
import model.InfoObj;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import serverProgram.STATE;

public class RandomPlayerController{

    @FXML
    public Button randomPlayer;

    // to go back to mainGameScreen
    public void loadMainGame() {
        ControllerUtil.changeScenes(ControllerUtil.getGameMenuScene());
    }

    // Creates a connection to the server, sends userName to server, sends that this player is ready to play a game
    public void loadRandomPlayer() {
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_PLAYERNAME, ControllerUtil.getGameMenuController().getUserNameLabel().getText()));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.READY_TO_PLAY));
        ControllerUtil.changeScenes(ControllerUtil.getSearchingForPlayerScene());
    }
}
