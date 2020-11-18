package serverProgram;

import Model.InfoObj;
import javafx.scene.effect.ImageInput;

import java.io.FileInputStream;
import java.util.Properties;

public class ServerProtocol {

    private int roundsPerGame;
    private int questionsPerRound;

    public ServerProtocol(){

    //---------------------------- To Set Game Rounds And Questions Per Round ---------------------------------\\

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/serverProgram/config.properties"));
            int tempRoundsPerGame = Integer.parseInt(properties.getProperty("roundsPerGame"));
            int tempQuestionsPerRound = Integer.parseInt(properties.getProperty("questionsPerRound"));
            if (tempRoundsPerGame >= 2 && tempRoundsPerGame <= 5
                    && tempQuestionsPerRound >= 2 && tempQuestionsPerRound <= 5){
                this.roundsPerGame = tempRoundsPerGame;
                this.questionsPerRound = tempQuestionsPerRound;
            }else{
                this.roundsPerGame = 2;
                this.questionsPerRound = 2;
            }
        }catch (Exception e){
            this.roundsPerGame = 2;
            this.questionsPerRound = 2;
            e.printStackTrace();
        }
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public int getQuestionsPerRound() {
        return questionsPerRound;
    }

    public static void main(String[] args) {
        // test the reading from propfile
        ServerProtocol s = new ServerProtocol();
        System.out.println(s.getRoundsPerGame());
        System.out.println(s.getQuestionsPerRound());

    }

    //-------------------------------------- To Handle Object From Player ------------------------------------------\\

    public synchronized void handleObject(Player player, InfoObj infoObj){



    }



}
