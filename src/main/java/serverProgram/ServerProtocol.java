package serverProgram;

import model.*;
import serverProgram.questionDAO.TriviaDB;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static model.STATE.*;

public class ServerProtocol {

    private int roundsPerGame;
    private int questionsPerRound;

    private int currentRound = 0;
    private int currentQuestion = 0;

    private Question question = null;

    private final ArrayList<ServerListener> playersList;
    private final TriviaDB triviaDB;
    private Game game;
    private Player player;
    private ServerListener serverListener;
    private List<Question> questionList = new ArrayList<>();


    //-------------------------------- To Set Game Rounds And Questions Per Round ---------------------------------\\

    public ServerProtocol() {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/serverProgram/config.properties"));
            int tempRoundsPerGame = Integer.parseInt(properties.getProperty("roundsPerGame"));
            int tempQuestionsPerRound = Integer.parseInt(properties.getProperty("questionsPerRound"));
            if (tempRoundsPerGame >= 2 && tempRoundsPerGame <= 5
                    && tempQuestionsPerRound >= 2 && tempQuestionsPerRound <= 5) {
                this.roundsPerGame = tempRoundsPerGame;
                this.questionsPerRound = tempQuestionsPerRound;
            } else {
                this.roundsPerGame = 2;
                this.questionsPerRound = 2;
            }
        } catch (Exception e) {
            this.roundsPerGame = 2;
            this.questionsPerRound = 2;
            e.printStackTrace();
        }
        triviaDB = new TriviaDB();
        playersList = new ArrayList<>();

    }

    public ArrayList<ServerListener> getPlayersList() {
        return playersList;
    }


    //-------------------------------------- To Handle Object From Player ------------------------------------------\\


    public void handleObject(ServerListener serverListener, InfoObj infoObj) {
        player = serverListener.getPlayer();
        this.serverListener = serverListener;

        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> setPlayerName(infoObj);
            case READY_TO_PLAY -> readyToPlay();
            case ASK_CATEGORY -> sendAskForCategoryToCurrentPlayer();
            case SET_CATEGORY -> setCategory(infoObj);
            case SEND_QUESTION -> sendQuestion();
            case HANDLE_ANSWER -> checkAnswer(infoObj);
        }
    }

    public void setPlayerName(InfoObj infoObj) {
        player.setPlayerName(infoObj.getMsg());
    }

    public void readyToPlay() {
        game = serverListener.getGame();
        serverListener.getPlayer().setReadyToPlay(true);
        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            setPlayersOpponents();
            setReadyToPlayFalseForPlayers();
            threadSleep(1000);
            sendToAllPlayers(new StartPackage(roundsPerGame, questionsPerRound));
            sendOpponentToAllPlayers(GO_TO_GAMEBOARD);
        }
    }

    public void sendAskForCategoryToCurrentPlayer() {
        serverListener.getPlayer().setReadyToPlay(true);
        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            sendToAllPlayers(new InfoObj(ASK_CATEGORY, game.getCurrentPlayer().getPlayerName()));
        }
    }

    public void setCategory(InfoObj infoObj) {
        questionList = triviaDB.getQuestionList(infoObj.getMsg());
        setReadyToPlayTrueForBothPlayers();
        game.switchCurrentPlayers();
        sendQuestion();

    }

    public void sendQuestion() {
        serverListener.getPlayer().setReadyToPlay(true);
        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            question = getQuestion();
            currentQuestion++;
            setReadyToPlayFalseForPlayers();
            sendToAllPlayers(question);
        }

    }

    private void checkAnswer(InfoObj infoObj) {
        player.setReadyToPlay(true);
        if (infoObj.getMsg().equalsIgnoreCase(question.getCorrectAnswer())) {
            player.addRoundPoint();
            player.addTotalPoint();
        }

        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            threadSleep(1500);
            if (currentQuestion < questionsPerRound) {
                sendQuestion();
            } else {
                currentQuestion = 0;
                currentRound++;
                setReadyToPlayFalseForPlayers();
                if (currentRound < roundsPerGame) {
                    sendOpponentToAllPlayers(GO_TO_GAMEBOARD);
                    resetPlayersRoundScores();
                } else {
                    sendOpponentToAllPlayers(GAME_OVER);
                }
            }
        }
    }

    //------------------------------------ Methods for ServerListener -------------------------------------------\\

    private void sendToAllPlayers(Object obj) {
        for (ServerListener l : playersList) {
            l.sendObj(obj);
        }
    }

    private void sendOpponentToAllPlayers(STATE state) {
        for (ServerListener l : playersList) {
            Player temp = new Player();
            temp.setPlayerName(l.getPlayer().getOpponent().getPlayerName());
            temp.setPlayerRoundScore(l.getPlayer().getOpponent().getPlayerRoundScore());
            temp.setPlayerTotalScore(l.getPlayer().getOpponent().getPlayerTotalScore());
            l.sendObj(new InfoObj(state, temp));
        }
    }

    private Question getQuestion(){
        Question tempQuestion = null;
        switch (currentQuestion) {
            case 0 -> tempQuestion = questionList.get(0);
            case 1 -> tempQuestion = questionList.get(1);
            case 2 -> tempQuestion = questionList.get(2);
            case 3 -> tempQuestion = questionList.get(3);
            case 4 -> tempQuestion = questionList.get(4);
        }
        return tempQuestion;
    }

    private void setReadyToPlayTrueForBothPlayers() {
        game.getPlayer1().setReadyToPlay(true);
        game.getPlayer2().setReadyToPlay(true);
    }

    private void setReadyToPlayFalseForPlayers() {
        game.getPlayer1().setReadyToPlay(false);
        game.getPlayer2().setReadyToPlay(false);
    }

    private void setPlayersOpponents(){
        game.getPlayer1().setOpponent(game.getPlayer2());
        game.getPlayer2().setOpponent(game.getPlayer1());
    }

    private void resetPlayersRoundScores(){
        game.getPlayer1().setPlayerRoundScore(0);
        game.getPlayer2().setPlayerRoundScore(0);
    }

    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
