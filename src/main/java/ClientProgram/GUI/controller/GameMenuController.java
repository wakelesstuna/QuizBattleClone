package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameMenuController {

    // GameMenu

    @FXML
    private Label userNameLabel;

    // loading the randomPlayerScene
    public void loadSelectPlayer(){
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

