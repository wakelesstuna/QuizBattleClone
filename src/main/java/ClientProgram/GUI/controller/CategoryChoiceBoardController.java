package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryChoiceBoardController implements Initializable {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();
    int test = 1;

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
        AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
        categoryChoiceBoard.getChildren().setAll(pane);
    }

    public void java(){
        // håmta fråga om java från server och visa på fråge texten
        Main.categoryName = " Java";
        AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
        categoryChoiceBoard.getChildren().setAll(pane);
    }

    public void literature(){
        // håmta fråga om literatur från server och visa på fråge texten
        Main.categoryName = " Literature";
        AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
        categoryChoiceBoard.getChildren().setAll(pane);
    }

    public void music(){
        // håmta fråga om musik från server och visa på fråge texten
        Main.categoryName = " Music";
        AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
        categoryChoiceBoard.getChildren().setAll(pane);
    }

    public void sports(){
        // håmta fråga om sport från server och visa på fråge texten
        Main.categoryName = " Sports";
        AnchorPane pane = c.loadFMXLFiles(currentClass, "questionBoard");
        categoryChoiceBoard.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      /*  if (test == 1){
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
*/
    }
}
