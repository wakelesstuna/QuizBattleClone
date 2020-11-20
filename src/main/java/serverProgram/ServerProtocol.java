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
    private int currentQuestion;

    private Player player;
    private ArrayList<ServerListner> playersList;
    Database database;
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

    public void handleObject(ServerListner serverListner, InfoObj infoObj) {

        player = serverListner.getPlayer();

        // different states of the game
        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> setPlayerName(player, infoObj);
            case READY_TO_PLAY -> readyToPlay(player, serverListner); //readyToPlay(player);
            case ASK_CATEGORY -> System.out.println(infoObj.getName()); //askCategory(player);
            case SET_CATEGORY -> System.out.println(infoObj.getName()); //setCategory(player,infoObj);
            case SEND_QUESTION -> System.out.println(infoObj.getName()); //sendQuestion(player);//player.sendObj(new InfoObj(STATE.SEND_QUESTION, questionTest));
            case HANDLE_ANSWER -> System.out.println(infoObj.getName()); //checkAnswer(player, infoObj);
            case GAME_OVER -> System.out.println(infoObj.getName());
        }
    }


    public void setPlayerName(Player player, InfoObj infoObj) {
        player.setPlayerName(infoObj.getName());
        System.out.println(player.getPlayerName() + " setting PlayerName");
    }

    public void readyToPlay(Player player, ServerListner serverListner) {
        System.out.println(player.getPlayerName() + " in readyToplay");
        player.setReadyToPlay(true);
        System.out.println(player.isReadyToPlay());


        // väntar på att alla player ska vara redo for att få frågor
        while (true) {
            // när all spelar anslutit så breakar loopen och man fortsätter med att skicka category choise to players
            if (serverListner.getGame().getPlayer1().isReadyToPlay() && serverListner.getGame().getPlayer2().isReadyToPlay()) {
                System.out.println("both ready to play");
                break;

            }
        }

        sendAskForCategoryToCurrentPlayer();

        serverListner.getGame().switchCurrentPlayers();

        sendAskForCategoryToCurrentPlayer();




    }
/*
    public void askCategory(Player player){
        if (player == player.getGame().getCurrentPlayer()){
            player.sendObj(new InfoObj(STATE.ASK_CATEGORY, new Category("SetCategory")));
            player.getGame().getNotCurrentPlayer().sendObj(new InfoObj(STATE.ASK_CATEGORY, new Category("WaitCategory")));
        }else{
            player.getGame().getCurrentPlayer().sendObj(new InfoObj(STATE.ASK_CATEGORY, new Category("SetCategory")));
            player.sendObj(new InfoObj(STATE.ASK_CATEGORY, new Category("WaitCategory")));
        }

    }

    public void setCategory(Player player, InfoObj infoObj) {
        player.getGame().setCurrentCategory(infoObj.getCategory());

        // nollställ fråge räknaren så man inte hamnar utanför index i questionlist
        currentQuestion = 0;
    }

    public void sendQuestion(Player player) {
        System.out.println("Sending question to player");
        if (currentRound < roundsPerGame){
            switch (currentQuestion) {
                case 0 -> player.sendObj(questionList.get(0));
                case 1 -> player.sendObj(questionList.get(1));
                case 2 -> player.sendObj(questionList.get(2));
                case 3 -> player.sendObj(questionList.get(3));
                case 4 -> player.sendObj(questionList.get(4));
                default -> player.sendObj("Score");
            }
            currentRound++;
            currentQuestion++;
        }

        //Question question = player.getGame().getQuestionList().get(currentQuestion);

    }

    private void checkAnswer(Player player, InfoObj infoObj) {
        player.setHasAnswer(true);
        if (infoObj.getAnswer().equalsIgnoreCase(infoObj.getQuestion().getCollectAnswer())){
            player.setPlayerRoundScore(player.getPlayerRoundScore() + 1);
            player.setPlayerTotalScore(player.getPlayerTotalScore() + 1);
        }

        while (true){
            if (player.getGame().getCurrentPlayer().isHasAnswer()
                    && player.getGame().getNotCurrentPlayer().isHasAnswer()){
                break;
            }
        }


    }*/


    public void sendAskForCategoryToCurrentPlayer(){

        for (ServerListner s : playersList){
            if (s.getGame().getCurrentPlayer() == s.getGame().getPlayer1()){
                s.sendObj(new InfoObj(STATE.ASK_CATEGORY, s.getGame().getPlayer1().getPlayerName()));
            }else if (s.getGame().getCurrentPlayer() == s.getGame().getPlayer2()){
                s.sendObj(new InfoObj(STATE.ASK_CATEGORY, s.getGame().getPlayer2().getPlayerName()));
            }
        }
    }

}
