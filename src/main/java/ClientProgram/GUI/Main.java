package ClientProgram.GUI;


import ClientProgram.GUI.controller.FxmlPaths;
import ClientProgram.PlayerConnection;
import Model.Question;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.List;

public class Main extends Application{

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(FxmlPaths.LOGIN_MENU.toString()));
        System.out.println("Starting Client App");
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

