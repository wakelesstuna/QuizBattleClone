package ClientProgram;

import ClientProgram.GUI.Main;
import ClientProgram.GUI.controller.SearchingForPlayerController;
import Model.InfoObj;
import Model.Question;

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

    public void checkObjectFromServer(Object objFromServer){

        if (objFromServer instanceof InfoObj){


            switch (((InfoObj) objFromServer).getState()){
                case READY_TO_PLAY -> System.out.println(((InfoObj) objFromServer).getName());
            }
        }else if (objFromServer instanceof Question){
            System.out.println("Question here");
            Main.question = (((Question) objFromServer));
        }else if(objFromServer instanceof List){

        }
    }
}
