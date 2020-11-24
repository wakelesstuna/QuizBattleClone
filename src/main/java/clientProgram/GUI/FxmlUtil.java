package clientProgram.GUI;

import clientProgram.GUI.controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.IFxmlPaths;

import java.io.IOException;

public class FxmlUtil implements IFxmlPaths {

    private static Stage currentStage;


    //--------------------------------------- TO LOAD LOGIN MENU ---------------------------------------------------\\

    private static final FXMLLoader loginMenuLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(LOGIN_MENU));
    private static Parent loginMenuParent;
    static { try { loginMenuParent = loginMenuLoader.load(); System.out.println("Loaded Login Menu");} catch (IOException e) { e.printStackTrace(); }}
    private static final Scene loginMenuScene = new Scene(loginMenuParent);

    public static Scene getLoginMenuScene() {
        return loginMenuScene;
    }

    //--------------------------------------- TO LOAD GAME MENU ---------------------------------------------------\\

    private static final FXMLLoader gameMenuLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(GAME_MENU));
    private static Parent gameMenuParent;
    static { try { gameMenuParent = gameMenuLoader.load(); System.out.println("Loaded Game Menu"); } catch (IOException e) { e.printStackTrace(); }}
    private static final Scene gameMenuScene = new Scene(gameMenuParent);
    private static final GameMenuController gameMenuController = gameMenuLoader.getController();

    public static Scene getGameMenuScene() {
        return gameMenuScene;
    }

    public static GameMenuController getGameMenuController(){
        return gameMenuController;
    }

    //--------------------------------------- TO LOAD RANDOM PLAYER ---------------------------------------------------\\

    private static final FXMLLoader randomPlayerLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(RANDOM_PLAYER));
    private static Parent randomPlayerParent;
    static { try { randomPlayerParent = randomPlayerLoader.load(); System.out.println("Loaded Random Player Menu");} catch (IOException e) { e.printStackTrace(); }}
    private static final Scene randomPlayerScene = new Scene(randomPlayerParent);
    private static final RandomPlayerController randomPlayerController = randomPlayerLoader.getController();

    public static Scene getRandomPlayerScene() {
        return randomPlayerScene;
    }

    public static RandomPlayerController getRandomPlayerController(){
        return randomPlayerController;
    }

    //--------------------------------------- TO LOAD SEARCHING FOR PLAYER ---------------------------------------------------\\

    private static final FXMLLoader searchingForPlayerLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(SEARCHING_FOR_PLAYER));
    private static Parent searchingForPlayerParent;
    static { try { searchingForPlayerParent = searchingForPlayerLoader.load(); System.out.println("Loaded Searching For Player Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static final Scene searchingForPlayerScene = new Scene(searchingForPlayerParent);

    public static Scene getSearchingForPlayerScene() {
        return searchingForPlayerScene;
    }

    //--------------------------------------- TO LOAD GAME BOARD ---------------------------------------------------\\

    private static final FXMLLoader gameBoardLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(GAME_BOARD));
    private static Parent gameBoardParent;
    static { try { gameBoardParent = gameBoardLoader.load(); System.out.println("Loaded Game Bord Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static final Scene gameBoardScene = new Scene(gameBoardParent);
    private static final GameBoardController gameBoardController = gameBoardLoader.getController();

    public static Scene getGameBoardScene() {
        return gameBoardScene;
    }

    public static GameBoardController getGameBoardController(){
        return gameBoardController;
    }

    //--------------------------------------- TO LOAD CATEGORY BOARD---------------------------------------------------\\

    private static final FXMLLoader categoryChoiceBoardLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(CATEGORY_BOARD));
    private static Parent categoryChoiceBoardParent;
    static { try { categoryChoiceBoardParent = categoryChoiceBoardLoader.load(); System.out.println("Loaded CateGory Board Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static final Scene categoryChoiceBoardScene = new Scene(categoryChoiceBoardParent);
    private static final CategoryChoiceBoardController categoryChoiceBoardController = categoryChoiceBoardLoader.getController();

    public static Scene getCategoryChoiceBoardScene() {
        return categoryChoiceBoardScene;
    }

    public static CategoryChoiceBoardController getCategoryChoiceBoardController (){ return categoryChoiceBoardController; }

    //--------------------------------------- TO LOAD QUESTION BOARD---------------------------------------------------\\

    private static final FXMLLoader questionBoardLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(QUESTION_BOARD));
    private static Parent questionBoardParent;
    static { try { questionBoardParent = questionBoardLoader.load(); System.out.println("Loaded Question Board Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static final Scene questionBoardScene = new Scene(questionBoardParent);
    private static final QuestionBoardController questionBoardController = questionBoardLoader.getController();

    public static Scene getQuestionBoardScene() {
        return questionBoardScene;
    }

    public static QuestionBoardController getQuestionBoardController (){
        return questionBoardController;
    }

    //--------------------------------------- TO LOAD FINAL RESULTS---------------------------------------------------\\

    private static final FXMLLoader finalResultsLoader = new FXMLLoader(FxmlUtil.class.getClassLoader().getResource(FINAL_RESULTS));
    private static Parent finalResultsParent;
    static { try { finalResultsParent = finalResultsLoader.load(); System.out.println("Loaded Final Results Scene");} catch (IOException e) { e.printStackTrace(); }}
    private static final Scene finalResultsScene = new Scene(finalResultsParent);
    private static final FinalResultsController finalResultsController = finalResultsLoader.getController();

    public static Scene getFinalResultsScene() { return finalResultsScene; }

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
