package serverProgram;

import model.*;
import serverProgram.questionDAO.Database;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static model.STATE.*;

public class ServerProtocol {

    private int roundsPerGame;
    private int questionsPerRound;

    private int currentRound = 0;
    private int currentCategory = 0;
    private int currentQuestion = 0;

    private Question question = null;

    private final ArrayList<ServerListener> playersList;
    private final Database database;
    private Game game;
    Player player;
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
        database = new Database();
        playersList = new ArrayList<>();

    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public int getQuestionsPerRound() {
        return questionsPerRound;
    }

    public ArrayList<ServerListener> getPlayersList() {
        return playersList;
    }

    // test the reading from config.properties
    public static void main(String[] args) {
        ServerProtocol s = new ServerProtocol();
        System.out.println(s.getRoundsPerGame());
        System.out.println(s.getQuestionsPerRound());

    }

    //-------------------------------------- To Handle Object From Player ------------------------------------------\\


    public void handleObject(ServerListener serverListener, InfoObj infoObj) {

        player = serverListener.getPlayer();

        // different states of the game
        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> setPlayerName(infoObj);
            case READY_TO_PLAY -> readyToPlay(serverListener);
            case ASK_CATEGORY -> System.out.println(infoObj.getMsg());
            case SET_CATEGORY -> setCategory(serverListener, infoObj);
            case SEND_QUESTION -> sendQuestion(serverListener);
            case HANDLE_ANSWER -> checkAnswer(player, serverListener, infoObj);
        }
    }

    public void setPlayerName(InfoObj infoObj) {
        player.setPlayerName(infoObj.getMsg());
    }

    public void readyToPlay(ServerListener serverListener) {
        game = serverListener.getGame();
        serverListener.getPlayer().setReadyToPlay(true);

        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            game.getPlayer1().setOpponent(game.getPlayer2());
            game.getPlayer2().setOpponent(game.getPlayer1());
            System.out.println("both ready to play");
            setReadyToPlayFalseForBothPlayers();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (ServerListener l : playersList) {

                l.sendObj(new StartPackage(roundsPerGame, questionsPerRound));
            }
            sendOpponentToAllPlayers(GO_TO_GAMEBOARD);
        }
    }


    public void sendAskForCategoryToCurrentPlayer() {
        for (ServerListener s : playersList) {
            if (s.getGame().getCurrentPlayer() == s.getGame().getPlayer1()) {
                s.sendObj(new InfoObj(STATE.ASK_CATEGORY, s.getGame().getPlayer1().getPlayerName()));
            } else if (s.getGame().getCurrentPlayer() == s.getGame().getPlayer2()) {
                s.sendObj(new InfoObj(STATE.ASK_CATEGORY, s.getGame().getPlayer2().getPlayerName()));
            }
        }
    }

    public void setCategory(ServerListener serverListener, InfoObj infoObj) {
        // hämtar listan med den kategorin användaren skickar in
        questionList = database.getQuestionList(infoObj.getMsg());

        // nollställ fråge räknaren så man inte hamnar utanför index i questionlist
        currentQuestion = 0;

    }

    public void sendQuestion(ServerListener serverListener) {

        serverListener.getPlayer().setReadyToPlay(true);

        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {

            questionList = database.getCategories().get(currentCategory).getQuestions();

            if (currentRound < roundsPerGame) {
                if (currentQuestion < questionsPerRound) {
                    switch (currentQuestion) {
                        case 0 -> question = questionList.get(0);
                        case 1 -> question = questionList.get(1);
                        case 2 -> question = questionList.get(2);
                        case 3 -> question = questionList.get(3);
                        case 4 -> question = questionList.get(4);
                    }
                    currentQuestion++;
                    setReadyToPlayFalseForBothPlayers();
                    sendToAllPlayers(question);
                }
            } else {

                //sendToAllPlayers(new InfoObj(GAME_OVER));
            }


        } else {
            System.out.println("Waiting for " + serverListener.getPlayer().getOpponent().getPlayerName() + " to be ready");
        }

    }

    private void checkAnswer(Player player, ServerListener serverListener, InfoObj infoObj) {
        player.setReadyToPlay(true);

        if (infoObj.getMsg().equalsIgnoreCase(question.getCorrectAnswer())) {
            player.addRoundPoint();
            player.addTotalPoint();
        }

        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("currentQuestion: " + currentQuestion);
            System.out.println("questionsPerRound: " + questionsPerRound);
            if (currentQuestion < questionsPerRound) {
                sendQuestion(serverListener);
            } else {
                currentQuestion = 0;
                currentCategory++;
                currentRound++;
                setReadyToPlayFalseForBothPlayers();

                if (currentRound < roundsPerGame) {

                    sendOpponentToAllPlayers(GO_TO_GAMEBOARD);

                    game.getPlayer1().setPlayerRoundScore(0);
                    game.getPlayer2().setPlayerRoundScore(0);

                } else {
                    sendOpponentToAllPlayers(GAME_OVER);
                }
            }
        } else {
            System.out.println("Väntar på att den andra spelaren ska svara");
        }
    }

    public void sendToAllPlayers(Object obj) {
        for (ServerListener l : playersList) {
            l.sendObj(obj);
        }
    }

    public void sendOpponentToAllPlayers(STATE state) {
        for (ServerListener l : playersList) {
            Player temp = new Player();
            temp.setPlayerName(l.getPlayer().getOpponent().getPlayerName());
            temp.setPlayerRoundScore(l.getPlayer().getOpponent().getPlayerRoundScore());
            temp.setPlayerTotalScore(l.getPlayer().getOpponent().getPlayerTotalScore());
            l.sendObj(new InfoObj(state, temp));
        }
    }

    public void setReadyToPlayFalseForBothPlayers() {
        game.getPlayer1().setReadyToPlay(false);
        game.getPlayer2().setReadyToPlay(false);
    }

}
