package serverProgram;

import Model.Category;
import Model.InfoObj;
import Model.Question;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class ServerProtocol {

    private int roundsPerGame;
    private int questionsPerRound;

    private int currentRound = 0;
    private int currentQuestion = 0;

    private ArrayList<Player> playersList;

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
        playersList = new ArrayList<>();
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public int getQuestionsPerRound() {
        return questionsPerRound;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    // test the reading from propfile
    public static void main(String[] args) {
        ServerProtocol s = new ServerProtocol();
        System.out.println(s.getRoundsPerGame());
        System.out.println(s.getQuestionsPerRound());

    }

    //-------------------------------------- To Handle Object From Player ------------------------------------------\\

    public synchronized void handleObject(Player player, InfoObj infoObj) {

        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> setPlayerName(player,infoObj);
            case ASK_CATEGORY -> askCategory(player);
            case SET_CATEGORY -> setCategory(player,infoObj);
            case SEND_QUESTION -> sendQuestion(player);
            case HANDLE_ANSWER -> checkAnswer(player, infoObj);
            case GAME_OVER -> System.out.println("");
        }
    }

    public void setPlayerName(Player player, InfoObj infoObj) {
        player.setPlayerName(infoObj.getName());
    }

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
        Question question = player.getGame().getQuestionList().get(currentQuestion);
        currentQuestion++;
        for (Player p : playersList) {
            p.sendObj(question);
        }
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


    }


}
