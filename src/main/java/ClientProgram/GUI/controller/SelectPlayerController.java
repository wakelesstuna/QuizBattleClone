package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import ClientProgram.PlayerConnection;
import Model.InfoObj;
import assets.IFxmlPaths;
import assets.IPort;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import serverProgram.STATE;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class SelectPlayerController implements IPort, IFxmlPaths {
    ControllerHelper ch = new ControllerHelper();
    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();



    @FXML
    private AnchorPane newGame;

    @FXML
    private Button makeConnectToServer;

    public void connectToServer(){
        try {
            Socket connectToServer = new Socket("127.0.0.1", PORT);
            ObjectOutputStream out = new ObjectOutputStream(connectToServer.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(connectToServer.getInputStream());
            ch.setObjout(out);
            ch.setObjin(in);

            ch.getObjout().writeObject(new InfoObj(STATE.GAME_OVER, "hej"));
            ch.changeScene(ControllerHelper.SEARCHING_FOR_PLAYER, makeConnectToServer);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMainGame() {

        c.changeScene(GAME_MENU,makeConnectToServer);
        //AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
        //newGame.getChildren().setAll(pane);
    }

    public void loadRandomPlayer() {
        Main.playerConnection = new PlayerConnection("127.0.0.1", 45455);
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SET_PLAYERNAME, Main.playerName));
        c.changeScene(RANDOM_PLAYER, makeConnectToServer);
        // skicka till servern att man är i status att leta spelare att spela mot
    }
}
