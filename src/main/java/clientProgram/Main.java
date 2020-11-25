package clientProgram;

import clientProgram.GUI.FxmlUtil;
import model.FxmlPathsImp;
import model.Question;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application implements FxmlPathsImp {

    public static PlayerConnection playerConnection;
    public static String playerName = "playerName";
    public static String categoryName = "";
    public static int choseQuestionTurn;
    public static Question question;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(LOGIN_MENU)));
        System.out.println("Starting Client App");
        primaryStage.setTitle("BestQuizBattleEver");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        FxmlUtil.setCurrentStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


