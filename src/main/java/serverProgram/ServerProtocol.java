package serverProgram;

import Model.*;
import serverProgram.databas.Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerProtocol {

    private int roundsPerGame;
    private int questionsPerRound;

    private int currentRound = 0;
    private int currentQuestion;
    private String currentCategory;

    private Player player;
    private ArrayList<ServerListner> playersList;
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

    public void handleObject(ServerListner serverListner, InfoObj infoObj) {

        player = serverListner.getPlayer();

        // different states of the game
        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> setPlayerName(player, infoObj);
            case READY_TO_PLAY -> serverListner.sendObj(new InfoObj(STATE.CHANGE_SCENE));//readyToPlay(player, serverListner); //readyToPlay(player);
            case ASK_CATEGORY -> System.out.println(infoObj.getName()); //askCategory(player);
            case SET_CATEGORY -> setCategory(serverListner, infoObj);
            case SEND_QUESTION -> sendQuestion(serverListner);
            case HANDLE_ANSWER -> System.out.println(infoObj.getName()); //checkAnswer(player, infoObj);
            case GAME_OVER -> System.out.println(infoObj.getName());
        }
    }


    public void setPlayerName(Player player, InfoObj infoObj) {
        player.setPlayerName(infoObj.getName());
        System.out.println(player.getPlayerName() + " setting PlayerName");
    }

    public void readyToPlay(Player player, ServerListner serverListner) {
        System.out.println(player.getPlayerName() + " in readyToPlay");
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

       /* Question question = database.getQuestionList("java").get(0);

        for (ServerListner l: playersList){
            l.sendObj(question);
        }*/




        //sendAskForCategoryToCurrentPlayer();
        //serverListner.getGame().switchCurrentPlayers();
    }

    public void sendToAllPlayers(ServerListner serverListner, Object obj){
        for (ServerListner l: playersList){
            l.sendObj(obj);
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
        questionList = database.getQuestionList(infoObj.getName());

        // nollställ fråge räknaren så man inte hamnar utanför index i questionlist
        currentQuestion = 0;

        //fake test question
        List<String> answers = new ArrayList<>();
        String s = "s";
        answers.add(s);
        s = "w";
        answers.add(s);
        s= "q";
        answers.add(s);
        s= "d";
        answers.add(s);

        Question questionTest = new Question("Vad heter du?", "Oscar", answers);

        // skickar först frågan
        serverListner.sendObj(new InfoObj(STATE.SEND_QUESTION, questionTest));
    }

    public void sendQuestion(ServerListner serverListner) {
        System.out.println("Sending question to player");
        Question question = null;
        if (currentRound < roundsPerGame) {
            switch (currentQuestion) {
                case 0 -> question = questionList.get(0);
                case 1 -> question = questionList.get(1);
                // case 2 -> question = questionList.get(2);
                // case 3 -> question = questionList.get(3);
                // case 4 -> question = questionList.get(4);
            }
            currentRound++;
            currentQuestion++;

            for (ServerListner l: playersList){
                l.sendObj(question);
            }
        }else {

            sendToAllPlayers(serverListner, new InfoObj(STATE.GAME_OVER, "Game Over"));
        }

    }

    private void checkAnswer(ServerListner serverListner, InfoObj infoObj) {
        player.setHasAnswered(true);
        if (infoObj.getAnswer().equalsIgnoreCase(infoObj.getQuestion().getCollectAnswer())){
            player.setPlayerRoundScore(player.getPlayerRoundScore() + 1);
            player.setPlayerTotalScore(player.getPlayerTotalScore() + 1);
        }

        while (true) {
            // när all spelar anslutit så breakar loopen och man fortsätter med att skicka category choise to players
            if (serverListner.getGame().getPlayer1().isHasAnswered() && serverListner.getGame().getPlayer2().isHasAnswered()) {
                System.out.println("both player answered the question");
                break;
            }
        }


    }

}
