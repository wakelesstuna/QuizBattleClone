package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class CategoryChoiceBoardController {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    @FXML
    private AnchorPane categoryChoiceBoard;


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
}
