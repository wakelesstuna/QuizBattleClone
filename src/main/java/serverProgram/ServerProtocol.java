package serverProgram;

import model.*;
import serverProgram.questionDAO.Database;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
        System.out.println("tar hand om object");
        Player player = serverListener.getPlayer();

        // different states of the game
        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> setPlayerName(player, infoObj);
            case READY_TO_PLAY -> readyToPlay(player, serverListener); //readyToPlay(player);
            case ASK_CATEGORY -> System.out.println(infoObj.getMsg()); //askCategory(player);
            case SET_CATEGORY -> setCategory(serverListener, infoObj);
            case SEND_QUESTION -> sendQuestion(serverListener);
            case HANDLE_ANSWER -> checkAnswer(player, serverListener, infoObj);
            case GAME_OVER -> System.out.println(infoObj.getMsg());
        }
    }

    public void setPlayerName(Player player, InfoObj infoObj) {
        player.setPlayerName(infoObj.getMsg());
        System.out.println(player.getPlayerName() + " setting PlayerName");
    }

    public void readyToPlay(Player player, ServerListener serverListener) {
        game = serverListener.getGame();
        System.out.println(player.getPlayerName() + " in readyToPlay");
        serverListener.getPlayer().setReadyToPlay(true);
        System.out.println(player.isReadyToPlay() + player.getPlayerName());
        // waits for 2 player to connect to game and be ready to play

        System.out.println(playersList.size());
        System.out.println(serverListener.getPlayer().getPlayerName());

        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            game.getPlayer1().setOpponent(game.getPlayer2());
            game.getPlayer2().setOpponent(game.getPlayer1());
            System.out.println("both ready to play");
            setReadyToPlayFalseForBothPlayers(serverListener);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (ServerListener l : playersList) {
                // to send the opponents name to the player
                Player opponent = l.getPlayer().getOpponent();
                l.sendObj(new StartPackage(roundsPerGame, questionsPerRound));
                l.sendObj(new InfoObj(STATE.CHANGE_SCENE, "gameBoard", opponent));
            }
        } else {
            System.out.println("väntar på spelare att bli redo");
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
        game = serverListener.getGame();
        serverListener.getPlayer().setReadyToPlay(true);

        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            questionList = database.getCategories().get(currentCategory).getQuestions();
            System.out.println(currentRound);
            System.out.println(roundsPerGame);
            if (currentRound < roundsPerGame) {
                if (currentQuestion < questionsPerRound) {
                    switch (currentQuestion) {
                        case 0 -> question = questionList.get(0);
                        case 1 -> question = questionList.get(1);
                        // case 2 -> question = questionList.get(2);
                        // case 3 -> question = questionList.get(3);
                        // case 4 -> question = questionList.get(4);
                    }
                    currentQuestion++;
                    setReadyToPlayFalseForBothPlayers(serverListener);
                    sendToAllPlayers(question);
                }
            } else {
                // här skickar vi till clienterna att denna ska byta scene till finalscore
                // vi skickar med båda spelarna så dom kan sätta värde på de grafiska variablerna
                // i finalscore
                sendToAllPlayers(new InfoObj(STATE.GAME_OVER));
            }


        } else {
            System.out.println("Waiting for " + serverListener.getPlayer().getOpponent().getPlayerName() + " to be ready");
        }

    }

    private void checkAnswer(Player player, ServerListener serverListener, InfoObj infoObj) {
        game = serverListener.getGame();
        player.setReadyToPlay(true);

        if (infoObj.getMsg().equalsIgnoreCase(question.getCorrectAnswer())) {
            player.addRoundPoint();
            System.out.println("inne och sätter score " + player.getPlayerName());
            player.addTotalPoint();
            System.out.println(player.getPlayerName() + player.getPlayerRoundScore());
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
                setReadyToPlayFalseForBothPlayers(serverListener);
                if (currentRound < roundsPerGame) {
                    for (ServerListener l : playersList) {
                        // to send the opponents name to the player
                        System.out.println(l.getPlayer());
                        System.out.println(l.getPlayer() + " " + l.getPlayer().getPlayerRoundScore() + "total: " + l.getPlayer().getPlayerTotalScore());
                        System.out.println(l.getPlayer().getOpponent() + " " + l.getPlayer().getOpponent().getPlayerRoundScore());
                        l.sendObj(new InfoObj(STATE.CHANGE_SCENE, "gameBoard", 2));
                        l.getPlayer().setPlayerRoundScore(0);
                    }
                } else {
                    sendToAllPlayers(new InfoObj(STATE.GAME_OVER));
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

    public void setReadyToPlayFalseForBothPlayers(ServerListener serverListener) {
        serverListener.getGame().getPlayer1().setReadyToPlay(false);
        serverListener.getGame().getPlayer2().setReadyToPlay(false);
    }

}
