package clientProgram.GUI.controllers;

import clientProgram.Main;
import model.InfoObj;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.STATE;

public class CategoryChoiceBoardController{

    @FXML
    private AnchorPane categoryChoiceBoard;

    @FXML
    private Button geography;

    @FXML
    private Button java;

    @FXML
    private Button literature;

    @FXML
    private Button music;

    @FXML
    private Button sports;


    public void geography(){
        // håmta fråga om georafi från server och visa på fråge texten
        Main.categoryName = " GeoGraphy";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "geografi"));
    }

    public void java(){
        // håmta fråga om java från server och visa på fråge texten
        Main.categoryName = " Java";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "java"));
    }

    public void literature(){
        // håmta fråga om literatur från server och visa på fråge texten
        Main.categoryName = " Literature";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "litteratur"));
    }

    public void music(){
        // håmta fråga om musik från server och visa på fråge texten
        Main.categoryName = " Music";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "musik"));
    }

    public void sports(){
        // håmta fråga om sport från server och visa på fråge texten
        Main.categoryName = " Sports";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "sport"));
    }

}
