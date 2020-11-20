package serverProgram;

import Model.Category;
import Model.InfoObj;
import Model.Question;
import serverProgram.databas.Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerProtocol {

    private int roundsPerGame;
    private int questionsPerRound;

    private int currentRound = 0;
    private int currentQuestion;

    private ArrayList<Player> playersList;
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

    Question question;
    public void handleObject(Player player, InfoObj infoObj) throws IOException {

        // testQuestion
        List<String> answers = new ArrayList<>();
        String a1 = "The Beatles";
        String a2 = "Michael Jackson";
        String a3 = "Madonna";
        String a4 = "Queen";
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);

        question = new Question("Vem har sålt mest skivor i världen?", "The Beatles", answers);
        questionList.add(question);
        question = new Question("Vem grundade java?", "The Beatles", answers);
        questionList.add(question);



        switch (infoObj.getState()) {
            case SET_PLAYERNAME -> System.out.println(infoObj.getName());//setPlayerName(player,infoObj);
            case ASK_CATEGORY -> askCategory(player);
            case SET_CATEGORY -> setCategory(player,infoObj);
            case SEND_QUESTION -> sendQuestion(player);//player.sendObj(new InfoObj(STATE.SEND_QUESTION, questionTest));
            case HANDLE_ANSWER -> checkAnswer(player, infoObj);
            case GAME_OVER -> player.sendObj(new InfoObj(STATE.GAME_OVER, infoObj.getName()));

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


    }


}
