package serverProgram;

import Model.*;
import serverProgram.databas.Database;

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



    private ArrayList<ServerListner> playersList;
    Game game;
    Database database;
    List<Category> categoryList = new ArrayList<>();
    List<Question> questionList = new ArrayList<>();


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

    public ArrayList<ServerListner> getPlayersList() {
        return playersList;
    }

    // test the reading from propfile
    public static void main(String[] args) {
        ServerProtocol s = new ServerProtocol();
        System.out.println(s.getRoundsPerGame());
        System.out.println(s.getQuestionsPerRound());

    }

    //-------------------------------------- To Handle Object From Player ------------------------------------------\\

    public void handleObject(ServerListner serverListner, Object obj) {

        System.out.println("kommer hit");

    }

    public void handleObject(ServerListner serverListner, InfoObj infoObj) {
        System.out.println("tar hand om object");
        Player player = serverListner.getPlayer();

        // different states of the game
        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> setPlayerName(player, infoObj);
            case READY_TO_PLAY -> readyToPlay(player, serverListner); //readyToPlay(player);
            case ASK_CATEGORY -> System.out.println(infoObj.getMsg()); //askCategory(player);
            case SET_CATEGORY -> setCategory(serverListner, infoObj);
            case SEND_QUESTION -> sendQuestion(serverListner);
            case HANDLE_ANSWER -> checkAnswer(player, serverListner, infoObj);
            case GAME_OVER -> System.out.println(infoObj.getMsg());
            case SEND_QUESTIONLIST -> sendQuestionList(serverListner, infoObj);
        }
    }

    // TODO: 2020-11-22 Servern får ej emot meddelandet från spelare 1 när man trycker på play knappen 

    private void sendQuestionList(ServerListner serverListner, InfoObj obj) {

        serverListner.getPlayer().setReadyToPlay(true);
        System.out.println(serverListner.getPlayer().getPlayerName() + " " + serverListner.getPlayer().isReadyToPlay());
        System.out.println(playersList.size());
        System.out.println("väntar på nästa spelare");

        if (serverListner.getGame().getPlayer1().isReadyToPlay() && serverListner.getGame().getPlayer2().isReadyToPlay()) {
            if (obj.getMsg().equals("java")) {
                playersList.forEach(l -> l.sendObj(database.getQuestionList(obj.getMsg())));
                //serverListner.sendObj(database.getQuestionList("java"));
                currentRound++;
            } else if (obj.getMsg().equals("geografi")) {
                playersList.forEach(l -> l.sendObj(database.getQuestionList(obj.getMsg())));
                //serverListner.sendObj(database.getQuestionList("geografi"));
                currentRound++;
            } else if (currentRound == 2) {
                serverListner.sendObj(database.getQuestionList("litteratur"));
                currentRound++;
            } else if (currentRound == 3) {
                serverListner.sendObj(database.getQuestionList("musik"));
                currentRound++;
            } else if (currentRound == 4) {
                serverListner.sendObj(database.getQuestionList("sport"));
                currentRound++;
            }

            //sendToAllPlayers(database.getQuestionList("java"));
            serverListner.sendObj(database.getQuestionList("java"));
            System.out.println("båda redo");
        }else {
            System.out.println("väntar på att bådda spelare ska vara redo");
        }

}


    public void setReadyToPlayFalseForBothPlayers(ServerListner serverListner){
        serverListner.getGame().getPlayer1().setReadyToPlay(false);
        serverListner.getGame().getPlayer2().setReadyToPlay(false);
    }

    public void sendToAllPlayers(ServerListner serverListner, Object obj) {
        for (ServerListner l : playersList) {
            l.sendObj(obj);
        }
    }


    public void setPlayerName(Player player, InfoObj infoObj) {
        player.setPlayerName(infoObj.getMsg());
        System.out.println(player.getPlayerName() + " setting PlayerName");
    }

    public void readyToPlay(Player player, ServerListner serverListner) {
        game = serverListner.getGame();
        System.out.println(player.getPlayerName() + " in readyToPlay");
        serverListner.getPlayer().setReadyToPlay(true);
        System.out.println(player.isReadyToPlay() + player.getPlayerName());
        // waits for 2 player to connect to game and be ready to play

        System.out.println(playersList.size());
        System.out.println(serverListner.getPlayer().getPlayerName());

        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            game.getPlayer1().setOpponent(game.getPlayer2());
            game.getPlayer2().setOpponent(game.getPlayer1());
            System.out.println("both ready to play");
            setReadyToPlayFalseForBothPlayers(serverListner);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (ServerListner l : playersList) {
                // to send the opponents name to the player
                Player opponent = l.getPlayer().getOpponent();
                l.sendObj(new InfoObj(STATE.CHANGE_SCENE, "gameBoard", opponent));
            }
        } else {
            System.out.println("väntar på spelare att bli redo");
        }

    }


    public void sendAskForCategoryToCurrentPlayer() {
        for (ServerListner s : playersList) {
            if (s.getGame().getCurrentPlayer() == s.getGame().getPlayer1()) {
                s.sendObj(new InfoObj(STATE.ASK_CATEGORY, s.getGame().getPlayer1().getPlayerName()));
            } else if (s.getGame().getCurrentPlayer() == s.getGame().getPlayer2()) {
                s.sendObj(new InfoObj(STATE.ASK_CATEGORY, s.getGame().getPlayer2().getPlayerName()));
            }
        }
    }

    public void setCategory(ServerListner serverListner, InfoObj infoObj) {
        // hämtar listan med den kategorin användaren skickar in
        questionList = database.getQuestionList(infoObj.getMsg());

        // nollställ fråge räknaren så man inte hamnar utanför index i questionlist
        currentQuestion = 0;

    }

    public void sendQuestion(ServerListner serverListner) {
        game = serverListner.getGame();
        serverListner.getPlayer().setReadyToPlay(true);


        if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()){
            questionList = database.getCategories().get(currentCategory).getQuestions();
            System.out.println(currentRound);
            System.out.println(roundsPerGame);
            if (currentRound < roundsPerGame){
                if (currentQuestion < questionsPerRound){
                    switch (currentQuestion) {
                        case 0 -> question = questionList.get(0);
                        case 1 -> question = questionList.get(1);
                        // case 2 -> question = questionList.get(2);
                        // case 3 -> question = questionList.get(3);
                        // case 4 -> question = questionList.get(4);
                    }
                    currentQuestion++;

                    setReadyToPlayFalseForBothPlayers(serverListner);
                    sendToAllPlayers(serverListner, question);
                }
            }else {
                // här skickar vi till clienterna att denna ska byta scene till finalscore
                // vi skickar med båda spelarna så dom kan sätta värde på de grafiska variablerna
                // i finalscore
                sendToAllPlayers(serverListner, new InfoObj(STATE.GAME_OVER));
            }



        }else {
            System.out.println("Waiting for " + serverListner.getPlayer().getOpponent().getPlayerName() + " to be ready");
        }

    }

    private void checkAnswer(Player player, ServerListner serverListner, InfoObj infoObj) {
        game = serverListner.getGame();
        System.out.println(question.getCollectAnswer());
        System.out.println(infoObj.getMsg());
        player.setReadyToPlay(true);

        if (infoObj.getMsg().equalsIgnoreCase(question.getCollectAnswer())) {
            player.setPlayerRoundScore(player.getPlayerRoundScore() + 1);
            System.out.println("inne och sätter score " + player.getPlayerName());
            player.setPlayerTotalScore(player.getPlayerTotalScore() + 1);
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
            if (currentQuestion < questionsPerRound){
                sendQuestion(serverListner);
            }else {
                currentQuestion = 0;
                currentCategory++;
                currentRound++;
                setReadyToPlayFalseForBothPlayers(serverListner);
                for (ServerListner l : playersList) {
                    // to send the opponents name to the player
                    Player opponent = l.getPlayer().getOpponent();
                    System.out.println(l.getPlayer().getPlayerName() + " " + l.getPlayer().getPlayerRoundScore() + "total: " + l.getPlayer().getPlayerTotalScore());
                    System.out.println(opponent.getPlayerName()+" " + opponent.getPlayerRoundScore());
                    l.sendObj(new InfoObj(STATE.CHANGE_SCENE, "gameBoard", opponent));
                    l.getPlayer().setPlayerRoundScore(0);
                }

            }

        } else {
            System.out.println("Väntar på att den andra spelaren ska svara");
        }


    }

}
