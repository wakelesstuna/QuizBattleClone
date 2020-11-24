package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import clientProgram.PlayerConnection;
import model.IIpConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameMenuController implements IIpConfig {


    @FXML
    private Label userNameLabel;

    public void loadSelectPlayer(){
        Main.playerConnection = new PlayerConnection(IPADDRESS, PORT);
        FxmlUtil.changeScenes(FxmlUtil.getRandomPlayerScene());
    }

    public void logout() {
        FxmlUtil.changeScenes(FxmlUtil.getLoginMenuScene());
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }
}

