package clientProgram.GUI.controller;

import clientProgram.GUI.ControllerUtil;
import clientProgram.GUI.Main;
import model.Category;
import model.InfoObj;
import model.IFxmlPaths;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import serverProgram.STATE;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryChoiceBoardController implements Initializable, IFxmlPaths {

    ControllerUtil c = new ControllerUtil();

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
        c.changeScene(FxmlPaths.QUESTION_BOARD.toString(), geography);
    }

    public void java(){
        // håmta fråga om java från server och visa på fråge texten
        Main.categoryName = " Java";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "java"));
        c.changeScene(FxmlPaths.QUESTION_BOARD.toString(), java);


    }

    public void literature(){
        // håmta fråga om literatur från server och visa på fråge texten
        Main.categoryName = " Literature";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, new Category("litteratur")));
        c.changeScene(FxmlPaths.QUESTION_BOARD.toString(), literature);
    }

    public void music(){
        // håmta fråga om musik från server och visa på fråge texten
        Main.categoryName = " Music";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "musik"));
        c.changeScene(FxmlPaths.QUESTION_BOARD.toString(), music);
    }

    public void sports(){
        // håmta fråga om sport från server och visa på fråge texten
        Main.categoryName = " Sports";
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_CATEGORY, "sport"));
        c.changeScene(FxmlPaths.QUESTION_BOARD.toString(), sports);
        //AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
        //categoryChoiceBoard.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Main.choseQuestionTurn == 1){
            geography.setDisable(true);
            java.setDisable(true);
            literature.setDisable(true);
            music.setDisable(true);
            sports.setDisable(true);
        }
        if (Main.categoryName.equals(" Java")){
            java.setVisible(false);
        }else if (Main.categoryName.equals(" Java")){

        }

    }
}
