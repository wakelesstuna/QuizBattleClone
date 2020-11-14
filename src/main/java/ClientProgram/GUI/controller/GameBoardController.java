package ClientProgram.GUI.controller;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    ControllerUtil c = new ControllerUtil();
    Class<?> currentClass = getClass();

    public static String opponent = "opponent"; // hämta motståndarnamn från servern
    public static int opponentPoints = 0;       // hämta motståndarscore från servern

    public static int numberOfRounds = 2; // sätt dessa med proptiesfil
    public static int numberOfQuestions = 2; // sätt dessa med proptiesfil

    public static int currentRound = 1;
    public static int gameRoundScore = 2;
    public static int gameTotalScore = 0;

    //public static Question currentQuestion; // här sätter vi frågan frpom servern


    @FXML
    private AnchorPane gameBoard;

    @FXML
    public static Button playButton;

    @FXML
    public static Button endGame;

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
    private Button p1R1Q1;

    @FXML
    private Button p1R1Q2;

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


    public void loadQuestion(){
        if (numberOfRounds == 0) {
            AnchorPane pane = c.loadFMXLFiles(currentClass, "finalResults");
            gameBoard.getChildren().setAll(pane);
        }else{
            // här behöver vi kolla vem av dom 2 spelarna som ska välja kategori
            AnchorPane pane = c.loadFMXLFiles(currentClass, "categoryChoiceBoard");
            gameBoard.getChildren().setAll(pane);
            currentRound++;
            numberOfRounds--;
        }
    }

    public void endGame(){
        AnchorPane pane = c.loadFMXLFiles(currentClass, "gameMenu");
        gameBoard.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rounds.setText("Round " + currentRound);
        youName.setText(Main.playerName);
        youScore.setText("" + gameRoundScore);
        opponentName.setText(opponent);
        opponentScore.setText("" + opponentPoints);
    }
}
