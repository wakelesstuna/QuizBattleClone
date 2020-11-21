package ClientProgram;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import ClientProgram.GUI.controller.FxmlPaths;
import Model.InfoObj;
import Model.Question;
import javafx.application.Platform;
import javafx.scene.Node;

import java.util.List;

public class PlayerProtocol {
    /**
     * I Main sparar vi alla vaiabler, som statiska variabler, vi behöver i spelet
     * som vi behöver komma åt från protokollet och kontrollerna
     * Man bygger upp de med att när man ska byta scene, så skickar man en fråga till
     * servern från kontrollern och frågar efter den infon man behöver använda i nästa scene
     * och sätter den infon till variablerna i main, sen byter man scene. Efter de laddar man
     * in alla variabler och data(från de statiska main variablerna) man behöver i den nästkommande scene
     * med hjälp av implementet initialize
     */

    ControllerUtil c = new ControllerUtil();


    public void checkObjectFromServer(Object objFromServer){

        if (objFromServer instanceof InfoObj){

            switch (((InfoObj) objFromServer).getState()){
                case CHANGE_SCENE -> loginMenu();
                case READY_TO_PLAY -> System.out.println(((InfoObj) objFromServer).getName());
                case ASK_CATEGORY -> askForcategory((InfoObj) objFromServer);
                case SEND_QUESTION -> Main.question = (Question) objFromServer;
            }

        }else if (objFromServer instanceof Question){
            System.out.println("Question here");
            Main.question = (((Question) objFromServer));
        }else if(objFromServer instanceof List){

        }
    }

    private void loginMenu() {
        Platform.runLater(()-> {
            ControllerUtil.changeScenes(ControllerUtil.getLoginMenuScene());
        });
    }

    private void askForcategory(InfoObj infoObj) {
        System.out.println("inne i askForCategory");
        if (infoObj.getName().equals(Main.playerName)){
            System.out.println("Din tur att välja category");
            Main.choseQuestionTurn = 0;
        }else {
            System.out.println("andra spelaren väljer category");
            Main.choseQuestionTurn = 1;
        }


    }
}
