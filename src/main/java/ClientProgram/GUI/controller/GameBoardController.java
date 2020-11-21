package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import Model.InfoObj;
import assets.IFxmlPaths;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import serverProgram.STATE;

import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable, IFxmlPaths {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    public static String opponent = "opponent"; // hämta motståndarnamn från servern
    public static int opponentPoints = 1;       // hämta motståndarscore från servern

    public static int numberOfRounds = 2; // sätt dessa med proptiesfil
    public static int numberOfQuestions = 2; // sätt dessa med proptiesfil

    public static int currentRound = 1;
    public static int gameRoundScore = 0;
    public static int gameTotalScore = 0;

    //public static Question currentQuestion; // här sätter vi frågan frpom servern

    @FXML
    private AnchorPane gameBoard;

    @FXML
    public Button playButton;

    @FXML
    public Button endGame;

    @FXML
    private Label rounds;

    @FXML
    private Label youScore;

    @FXML
    private Label opponentScore;

    @FXML
    private Label youName;

    @FXML
    private Label opponentName;

    @FXML
    private GridPane twoRounds;

    // player1 scoreButtons

    @FXML
    public static Button p1R1Q1;

    @FXML
    public static Button p1R1Q2;

    @FXML
    private Button p1R1Q3;

    @FXML
    private Button p1R2Q1;

    @FXML
    private Button p1R2Q2;

    @FXML
    private Button p1R2Q3;

    @FXML
    private Button p1R3Q1;

    @FXML
    private Button p1R3Q2;

    @FXML
    private Button p1R3Q3;

    // player2 scoreButtons

    @FXML
    private Button p2R1Q1;

    @FXML
    private Button p2R1Q2;

    @FXML
    private Button p2R1Q3;

    @FXML
    private Button p2R2Q1;

    @FXML
    private Button p2R2Q2;

    @FXML
    private Button p2R2Q3;

    @FXML
    private Button p2R3Q1;

    @FXML
    private Button p2R3Q2;

    @FXML
    private Button p2R3Q3;

    // TODO: 2020-11-17 ta bort knappar byt ut till siffror beroende på hur många rundor de blir


    public void loadQuestion(){
        Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SEND_QUESTION));
        c.changeScene(FxmlPaths.QUESTION_BOARD.toString(), playButton);

       /* if (numberOfRounds == 0) {
            currentRound--;
            System.out.println(currentRound);
            c.changeScene(FINAL_RESULTS, playButton);
            //AnchorPane pane = c.loadFMXLFiles(currentClass, "finalResults");
            //gameBoard.getChildren().setAll(pane);
        }else{
            //Main.playerConnection.sendObjectToServer(new InfoObj(STATE.SEND_QUESTION));
            // här behöver vi kolla vem av dom 2 spelarna som ska välja kategori
            c.changeScene(CATEGORY_BOARD, playButton);
            //AnchorPane pane = c.loadFMXLFiles(currentClass, "categoryChoiceBoard");
            //gameBoard.getChildren().setAll(pane);
            if (currentRound == 1){
                currentRound++;
            }
            System.out.println(currentRound);
            numberOfRounds--;
        }*/
    }

    public void endGame(){
        c.changeScene(GAME_MENU, playButton);
        //AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
        //gameBoard.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rounds.setText("Round " + currentRound);
        youName.setText(Main.playerName);
        youScore.setText("" + gameRoundScore);
        opponentName.setText(opponent);
        opponentScore.setText("" + opponentPoints);
        System.out.println(numberOfRounds);
        if (numberOfRounds == 0){
            playButton.setText("End Game");
        }
    }

    public Label getYouName() {
        return youName;
    }

    public Label getOpponentName() {
        return opponentName;
    }

}
