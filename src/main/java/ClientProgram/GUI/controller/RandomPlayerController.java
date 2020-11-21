package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import ClientProgram.PlayerConnection;
import Model.InfoObj;
import assets.IPort;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import serverProgram.STATE;

public class RandomPlayerController implements IPort{

    ControllerUtil c = new ControllerUtil();

    @FXML
    private Button makeConnectToServer;

    @FXML
    public Button randomPlayer;

    public void connectToServer(){

    }

    // to go back to mainGameScreen
    public void loadMainGame() {
        c.changeScene(FxmlPaths.LOGIN_MENU.toString(), makeConnectToServer);
    }

    //
    public void loadRandomPlayer() {
        Main.playerConnection = new PlayerConnection("127.0.0.1", 45455);
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_PLAYERNAME, Main.playerName));
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.READY_TO_PLAY));

        //c.changeScene(FxmlPaths.SEARCHING_FOR_PLAYER.toString(), makeConnectToServer);
    }
}
