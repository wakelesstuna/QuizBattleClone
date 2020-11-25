package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtilImp;
import clientProgram.Main;
import clientProgram.PlayerConnection;
import model.IpConfigImp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameMenuController implements IpConfigImp {


    @FXML
    private Label userNameLabel;

    public void loadSelectPlayer(){
        Main.playerConnection = new PlayerConnection(IPADDRESS, PORT);
        FxmlUtilImp.changeScenes(FxmlUtilImp.getRandomPlayerScene());
    }

    public void logout() {
        FxmlUtilImp.changeScenes(FxmlUtilImp.getLoginMenuScene());
    }

    public Label getUserNameLabel() {
        return userNameLabel;
    }
}

