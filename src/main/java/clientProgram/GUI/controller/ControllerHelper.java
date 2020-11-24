package clientProgram.GUI.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ControllerHelper {

    public static ObjectInputStream objin;
    public static ObjectOutputStream objout;
    public static Socket socket;
    public static String name;

    public static final String LOGIN = "loginmenu.fxml";
    public static final String GAME_MENU = "view/gameMenu.fxml";
    public static final String SEARCHING_FOR_PLAYER = "searchingForPlayer.fmxl";
    public static final String SELECT_PLAYER = "selectPlayer.fmxl";
    public static final String CATEGORY = "categoryChoiceBoard.fmxl";
    public static final String QUESTION_BOARD = "questionBoard.fmxl";
    public static final String FINAL_RESULT = "finalResults.fmxl";

    public void changeScene(String fxml, Node node){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObjectInputStream getObjin() {
        return objin;
    }

    public void setObjin(ObjectInputStream objin) {
        ControllerHelper.objin = objin;
    }

    public ObjectOutputStream getObjout() {
        return objout;
    }

    public void setObjout(ObjectOutputStream objout) {
        ControllerHelper.objout = objout;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        ControllerHelper.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ControllerHelper.name = name;
    }
}
