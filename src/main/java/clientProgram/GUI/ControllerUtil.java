package clientProgram.GUI;

import clientProgram.GUI.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUtil {

    private static Stage currentStage;
    public static String playerName = "";


    public void changeScene(String fxml, Node node){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            //Stage stage = (Stage) node.getScene().getWindow();
            Stage stage = currentStage;
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeScene(String fxml){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            //Stage stage = (Stage) node.getScene().getWindow();
            Stage stage = currentStage;
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //--------------------------------------- TO LOAD LOGIN MENU ---------------------------------------------------\\

    private static FXMLLoader loginMenuloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/loginMenu.fxml"));
    private static Parent loginMenuparent;
    static { try { loginMenuparent = loginMenuloader.load();
        System.out.println("Loaded Login Menu");} catch (IOException e) { e.printStackTrace(); }}
    private static Scene loginMenuScene = new Scene(loginMenuparent);
    private static LoginController loginController = loginMenuloader.getController();

    public static Scene getLoginMenuScene() {
        return loginMenuScene;
    }

    public static LoginController getLoginController(){
        return loginController;
    }

    //--------------------------------------- TO LOAD GAME MENU ---------------------------------------------------\\

    private static FXMLLoader gameMenuloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/gameMenu.fxml"));
    private static Parent gameMenuparent;
    static { try { gameMenuparent = gameMenuloader.load();
        System.out.println("Loaded Game Menu"); } catch (IOException e) { e.printStackTrace(); }}
    private static Scene gameMenuScene = new Scene(gameMenuparent);
    private static GameMenuController gameMenuController = gameMenuloader.getController();

    public static Scene getGameMenuScene() {
        return gameMenuScene;
    }

    public static GameMenuController getGameMenuController(){
        return gameMenuController;
    }

    //--------------------------------------- TO LOAD RANDOM PLAYER ---------------------------------------------------\\

    private static FXMLLoader randomPlayerloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/randomPlayer.fxml"));
    private static Parent randomPlayerparent;
    static { try { randomPlayerparent = randomPlayerloader.load();
        System.out.println("Loaded Random Player Menu");} catch (IOException e) { e.printStackTrace(); }}
    private static Scene randomPlayerScene = new Scene(randomPlayerparent);
    private static RandomPlayerController randomPlayerController = randomPlayerloader.getController();

    public static Scene getRandomPlayerScene() {
        return randomPlayerScene;
    }

    public static RandomPlayerController getRandomPlayerController(){
        return randomPlayerController;
    }

    //--------------------------------------- TO LOAD SEARCHING FOR PLAYER ---------------------------------------------------\\

    private static FXMLLoader searchingForPlayerloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/SearchingForPlayer.fxml"));
    private static Parent searchingForPlayerParent;
    static { try { searchingForPlayerParent = searchingForPlayerloader.load();
        System.out.println("Loaded Seraching For Player Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static Scene searchingForPlayerScene = new Scene(searchingForPlayerParent);
    private static SearchingForPlayerController searchingForPlayerController = searchingForPlayerloader.getController();

    public static Scene getSearchingForPlayerScene() {
        return searchingForPlayerScene;
    }

    public static SearchingForPlayerController getSearchingForPlayerController(){
        return searchingForPlayerController;
    }

    //--------------------------------------- TO LOAD GAME BOARD ---------------------------------------------------\\

    private static FXMLLoader gameBoardloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/gameBoard.fxml"));
    private static Parent gameBoardParent;
    static { try { gameBoardParent = gameBoardloader.load();
        System.out.println("Loaded Game Bord Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static Scene gameBoardScene = new Scene(gameBoardParent);
    private static GameBoardController gameBoardController = gameBoardloader.getController();

    public static Scene getGameBoardScene() {
        return gameBoardScene;
    }

    public static GameBoardController getGameBoardController(){
        return gameBoardController;
    }

    //--------------------------------------- TO LOAD CATEGORY BOARD---------------------------------------------------\\

    private static FXMLLoader categoryChoiceBoardloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/categoryChoiceBoard.fxml"));
    private static Parent categoryChoiceBoardParent;
    static { try { categoryChoiceBoardParent = categoryChoiceBoardloader.load();
        System.out.println("Loaded CateGory Board Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static Scene categoryChoiceBoardScene = new Scene(categoryChoiceBoardParent);
    private static CategoryChoiceBoardController categoryChoiceBoardController = categoryChoiceBoardloader.getController();

    public static Scene getCategoryChoiceBoardScene() {
        return categoryChoiceBoardScene;
    }

    public static CategoryChoiceBoardController getCategoryChoiceBoardController (){
        return categoryChoiceBoardController;
    }

    //--------------------------------------- TO LOAD QUESTION BOARD---------------------------------------------------\\

    private static FXMLLoader questionBoardloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/questionBoard.fxml"));
    private static Parent questionBoardParent;
    static { try { questionBoardParent = questionBoardloader.load();
        System.out.println("Loaded Question Board Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static Scene questionBoardScene = new Scene(questionBoardParent);
    private static QuestionBoardController questionBoardController = questionBoardloader.getController();

    public static Scene getQuestionBoardScene() {
        return questionBoardScene;
    }

    public static QuestionBoardController getQuestionBoardController (){
        return questionBoardController;
    }

    //--------------------------------------- TO LOAD FINAL RESULTS---------------------------------------------------\\

    private static FXMLLoader finalResultsloader = new FXMLLoader(ControllerUtil.class.getClassLoader().getResource("view/finalResults.fxml"));
    private static Parent finalResultsParent;
    static { try { finalResultsParent = finalResultsloader.load();  System.out.println("Loaded Final Results Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static Scene finalResultsScene = new Scene(finalResultsParent);
    private static FinalResultsController finalResultsController = finalResultsloader.getController();

    public static Scene getFinalResultsScene() {
        return finalResultsScene;
    }

    public static FinalResultsController getFinalResultsController (){
        return finalResultsController;
    }

    //------------------------------------- UTILS TO CHANGE SCENE AND SET -------------------------------------------------\\

    public static void changeScenes(Scene scene){
        currentStage.setScene(scene);
        currentStage.show();
    }

    public static void setCurrentStage(Stage primaryStage) {
        currentStage = primaryStage;
    }


}
