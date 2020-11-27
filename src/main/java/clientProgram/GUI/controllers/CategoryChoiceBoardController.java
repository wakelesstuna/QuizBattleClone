package clientProgram.GUI.controllers;

import clientProgram.Main;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import model.InfoObj;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import static model.STATE.*;

public class CategoryChoiceBoardController{

    @FXML
    private ProgressIndicator waitingIndicator;

    @FXML
    private Label whosTurnLabel;

    @FXML
    private Label choseACategoryLabel;

    @FXML
    private Button sports;

    @FXML
    private Button scienceAndNature;

    @FXML
    private Button animals;

    @FXML
    private Button geography;

    @FXML
    private Button history;

    public void sports(){
        Main.playerConnection.sendObjectToServer(new InfoObj(SET_CATEGORY, "sports"));
    }

    public void scienceAndNature(){
        Main.playerConnection.sendObjectToServer(new InfoObj(SET_CATEGORY, "scienceAndNature"));
    }

    public void animals(){
        Main.playerConnection.sendObjectToServer(new InfoObj(SET_CATEGORY, "animals"));
    }

    public void geography(){
        Main.playerConnection.sendObjectToServer(new InfoObj(SET_CATEGORY, "geography"));
    }

    public void history(){
        Main.playerConnection.sendObjectToServer(new InfoObj(SET_CATEGORY, "history"));
    }

    public ProgressIndicator getWaitingIndicator() {
        return waitingIndicator;
    }

    public Label getWhosTurnLabel() {
        return whosTurnLabel;
    }

    public Label getChoseACategoryLabel() {
        return choseACategoryLabel;
    }

    public Button getSports() {
        return sports;
    }

    public Button getScienceAndNature() {
        return scienceAndNature;
    }

    public Button getAnimals() {
        return animals;
    }

    public Button getGeography() {
        return geography;
    }

    public Button getHistory() {
        return history;
    }
}
