package clientProgram.GUI.controller;

import clientProgram.GUI.ControllerUtil;
import clientProgram.GUI.Main;
import clientProgram.PlayerConnection;
import model.IPort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameMenuController implements IPort{

    // GameMenu

    @FXML
    private Label userNameLabel;

    // loading the randomPlayerScene
    public void loadSelectPlayer(){
        Main.playerConnection = new PlayerConnection(IPADRRESS, PORT);
        ControllerUtil.changeScenes(ControllerUtil.getRandomPlayerScene());
    }

    // to logout to loginScene
    public void logout(ActionEvent event) {
        ControllerUtil.changeScenes(ControllerUtil.getLoginMenuScene());
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }
}

