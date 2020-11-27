package clientProgram;

import clientProgram.GUI.FxmlUtil;
import javafx.scene.image.Image;
import model.FxmlPathsImp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application implements FxmlPathsImp {

    public static PlayerListener playerListener;
    public static String playerName = "";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(LOGIN_MENU)));
        System.out.println("Starting Client App");
        primaryStage.setTitle("BestQuizBattleEver");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("images/mainLogo.png"));
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


