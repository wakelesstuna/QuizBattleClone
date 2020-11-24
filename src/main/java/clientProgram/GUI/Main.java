package clientProgram.GUI;


import clientProgram.GUI.controller.FxmlPaths;
import clientProgram.PlayerConnection;
import model.Question;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static PlayerConnection playerConnection;
    public static String playerName = "playerName";
    public static String categoryName = "";
    public static int choseQuestionTurn;
    public static Question question;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(FxmlPaths.LOGIN_MENU.toString()));
        System.out.println("Starting Client App");
        primaryStage.setTitle("BestQuizBattleEver");
        primaryStage.setResizable(false);
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


