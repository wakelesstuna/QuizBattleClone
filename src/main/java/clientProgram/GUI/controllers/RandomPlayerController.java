package clientProgram.GUI.controllers;

import clientProgram.GUI.FxmlUtil;
import clientProgram.Main;
import model.FxmlPathsImp;
import model.InfoObj;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static model.STATE.*;

public class RandomPlayerController implements FxmlPathsImp {

    @FXML
    public Button randomPlayer;

    public void loadMainGame() {
        try {
            Main.playerListener.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FxmlUtil.changeScenes(FxmlUtil.getGameMenuScene());
    }


    public void loadRandomPlayer() {
        Main.playerListener.sendObjectToServer(new InfoObj(SET_PLAYERNAME, Main.playerName));
        Main.playerListener.sendObjectToServer(new InfoObj(READY_TO_PLAY));
        FxmlUtil.changeScenes(FxmlUtil.getSearchingForPlayerScene());
    }
}
