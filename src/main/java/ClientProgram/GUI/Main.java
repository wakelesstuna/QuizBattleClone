package ClientProgram.GUI;

import ClientProgram.Client;
import ClientProgram.PlayerConnection;
import Model.Question;
import assets.IFxmlPaths;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class Main extends Application implements IFxmlPaths {

    public static PlayerConnection playerConnection;
    public static String playerName = "playerName";
    public static String categoryName = "";
    public static int currentRound = 1;
    public static int currentQuestion = 1;
    public static int choseQuestionTurn;
    public static Question question;
    public static List<Question> questionsList;



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(LOGIN_MENU));
        primaryStage.setTitle("BestQuizBattleEver");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        // Detta stänger hela programmet om man tycker på X i någon av rutorna
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        ControllerUtil.setCurrentStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
 * Få användarnamenet att komma till mainGame
 * Bygga searchingForPlayer scene
 * Bygga gameBoard scene
 * Bygga categoriChoieBoard scene
 * Bygga questionBoard scene
 *
 *
 *
 *
 *
 */

