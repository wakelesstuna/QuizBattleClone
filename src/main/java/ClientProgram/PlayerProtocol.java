package ClientProgram;

import ClientProgram.GUI.ControllerUtil;
import ClientProgram.GUI.Main;
import Model.InfoObj;
import Model.Question;
import javafx.application.Platform;

import java.util.List;

public class PlayerProtocol {
    /**
     * I Main sparar vi alla vaiabler, som statiska variabler, vi behöver i spelet
     * som vi behöver komma åt från protokollet och kontrollerna
     * Man bygger upp de med att när man ska byta scene, så skickar man en fråga till
     * servern från kontrollern och frågar efter den infon man behöver använda i nästa scene
     * och sätter den infon till variablerna i main, sen byter man scene. Efter de laddar man
     * in alla variabler och data(från de statiska main variablerna) man behöver i den nästkommande scene
     * med hjälp av implementet initialize
     */

    public void checkObjectFromServer(Object objFromServer){

        if (objFromServer instanceof InfoObj){

            switch (((InfoObj) objFromServer).getState()){
                case CHANGE_SCENE -> loginMenu((InfoObj)objFromServer);
                case READY_TO_PLAY -> System.out.println(((InfoObj) objFromServer).getMsg());
                case ASK_CATEGORY -> askForcategory((InfoObj) objFromServer);
                case SEND_QUESTION -> Main.question = (Question) objFromServer;
                case GAME_OVER -> finalScore();
            }

        }else if (objFromServer instanceof Question){
            System.out.println("Question here");
            Platform.runLater(() -> {
                ControllerUtil.getGameBoardController().playButton.setDisable(false);
                ControllerUtil.getQuestionBoardController().getAnswerButtonsList().forEach(button -> {button.setDisable(false); button.setStyle("-fx-background-color: #D1FDFF");});

                ControllerUtil.getQuestionBoardController().setQuestion(((Question) objFromServer));
                ControllerUtil.getQuestionBoardController().getCategoryLabel().setText(((Question) objFromServer).getCategoryName());
                ControllerUtil.getQuestionBoardController().getQuestionField().setText(((Question) objFromServer).getQuestion());

                // TODO: 2020-11-21 fixa så frågorna skrivs ut på rätt sätt

                for (int i = 0; i < ControllerUtil.getQuestionBoardController().getAnswerButtonsList().size(); i++) {
                    ControllerUtil.getQuestionBoardController().getAnswerButtonsList()
                            .get(i).setText(((Question) objFromServer).getAnswerChoices().get(i));
                }

                ControllerUtil.getQuestionBoardController().getWaitingInicator().setVisible(false);
                ControllerUtil.getQuestionBoardController().getWaitingLabel().setVisible(false);

                ControllerUtil.changeScenes(ControllerUtil.getQuestionBoardScene());
            });

        }else if(objFromServer instanceof List){
            Platform.runLater(()->{

                ControllerUtil.getGameBoardController().playButton.setDisable(false);
                ControllerUtil.getQuestionBoardController().getAnswerButtonsList().forEach(button -> {button.setDisable(false); button.setStyle("-fx-background-color: #D1FDFF");});

                System.out.println("QuestionList here");
                System.out.println(((List<Question>)objFromServer).get(0).getQuestion());

                // sets the list of questions for the round
                ControllerUtil.getQuestionBoardController().setQuestionList((List<Question>) objFromServer);

                // sets the first question
                ControllerUtil.getQuestionBoardController().setQuestion(((List<Question>) objFromServer).get(0));

                // sets the categoryLabel
                ControllerUtil.getQuestionBoardController().getCategoryLabel().setText("JAVA");

                // sets the text of the first question
                ControllerUtil.getQuestionBoardController().getQuestionField().setText(((List<Question>) objFromServer).get(0).getQuestion());

                // sets the answers to buttons for the first question
                for (int i = 0; i < ControllerUtil.getQuestionBoardController().getAnswerButtonsList().size(); i++) {
                    ControllerUtil.getQuestionBoardController().getAnswerButtonsList()
                            .get(i).setText(((List<Question>) objFromServer).get(0).getAnswerChoices().get(i)); }

                // loads the QuestionScene after all variables and GUI are set
                ControllerUtil.changeScenes(ControllerUtil.getQuestionBoardScene());
            });


        }
    }

    private void loginMenu(InfoObj objFromServer) {
        System.out.println("dags att byta scene");
        System.out.println(objFromServer.getPlayer().getPlayerName());
        System.out.println("" + objFromServer.getPlayer().getPlayerRoundScore());

            Platform.runLater(() -> {
                ControllerUtil.getGameBoardController().getWithRoundNumberLabel().setText("" + ControllerUtil.getGameBoardController().getWhichRoundNumber());
                ControllerUtil.getGameBoardController().getOpponentName().setText(objFromServer.getPlayer().getPlayerName());
                ControllerUtil.getGameBoardController().getOpponentRound1Score().setText("" + (objFromServer.getPlayer().getPlayerRoundScore()));
                ControllerUtil.changeScenes(ControllerUtil.getGameBoardScene());
            });

    }

    public void finalScore(){
        Platform.runLater(()-> {
            ControllerUtil.changeScenes(ControllerUtil.getGameMenuScene());
        });
    }


    private void askForcategory(InfoObj infoObj) {
        System.out.println("inne i askForCategory");
        if (infoObj.getMsg().equals(Main.playerName)){
            System.out.println("Din tur att välja category");
            Main.choseQuestionTurn = 0;
        }else {
            System.out.println("andra spelaren väljer category");
            Main.choseQuestionTurn = 1;
        }


    }
}
