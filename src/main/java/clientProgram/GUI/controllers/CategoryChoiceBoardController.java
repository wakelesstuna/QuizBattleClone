package clientProgram.GUI.controllers;

import clientProgram.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import model.InfoObj;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static model.STATE.*;

public class CategoryChoiceBoardController implements Initializable {

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
        Main.playerListener.sendObjectToServer(new InfoObj(SET_CATEGORY, "sports"));
    }

    public void scienceAndNature(){
        Main.playerListener.sendObjectToServer(new InfoObj(SET_CATEGORY, "scienceAndNature"));
    }

    public void animals(){
        Main.playerListener.sendObjectToServer(new InfoObj(SET_CATEGORY, "animals"));
    }

    public void geography(){
        Main.playerListener.sendObjectToServer(new InfoObj(SET_CATEGORY, "geography"));
    }

    public void history(){
        Main.playerListener.sendObjectToServer(new InfoObj(SET_CATEGORY, "history"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sports.setStyle("-fx-background-color: #D1FDFF");
        scienceAndNature.setStyle("-fx-background-color: #D1FDFF");
        animals.setStyle("-fx-background-color: #D1FDFF");
        geography.setStyle("-fx-background-color: #D1FDFF");
        history.setStyle("-fx-background-color: #D1FDFF");
    }
}