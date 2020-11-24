package clientProgram;

import clientProgram.GUI.ControllerUtil;
import clientProgram.GUI.Main;
import clientProgram.GUI.controller.FinalResultsController;
import clientProgram.GUI.controller.GameBoardController;
import model.InfoObj;
import model.Question;
import model.StartPackage;
import javafx.application.Platform;

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

                ControllerUtil.getQuestionBoardController().getCategoryLabel().setText("Java");

                ControllerUtil.getGameBoardController().playButton.setDisable(false);
                ControllerUtil.getQuestionBoardController().getAnswerButtonsList().forEach(button -> {button.setDisable(false); button.setStyle("-fx-background-color: #D1FDFF");});

                ControllerUtil.getQuestionBoardController().setQuestion(((Question) objFromServer));
                ControllerUtil.getQuestionBoardController().getCategoryLabel().setText("JAVA");
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

        }else if(objFromServer instanceof StartPackage){
            GameBoardController.numberOfRounds = ((StartPackage) objFromServer).getGameRounds();
            GameBoardController.numberOfQuestions = ((StartPackage) objFromServer).getQuestionPerRounds();
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
            FinalResultsController FRC = ControllerUtil.getFinalResultsController();
            GameBoardController GBC = ControllerUtil.getGameBoardController();

            if (Integer.parseInt(GBC.getYourTotalScore().getText()) > GBC.opponentPoints){
                FRC.getYouFinalScore().setText(String.valueOf(Integer.parseInt(GBC.getYourTotalScore().getText())));
                FRC.getWhoWinLabel().setText("YOU WIN!");
                FRC.getWinnerMsgLabel().setText("CONGRATULATIONS!");
            }else if (Integer.parseInt(GBC.getYourTotalScore().getText()) == GameBoardController.opponentPoints){
                FRC.getYouFinalScore().setText(String.valueOf(Integer.parseInt(GBC.getYourTotalScore().getText())));
                FRC.getWhoWinLabel().setText("IT'S A TIE");
                FRC.getWinnerMsgLabel().setText("BETTER LUCK\nNEXT TIME!");
            }else {
                FRC.getYouFinalScore().setText(String.valueOf(Integer.parseInt(GBC.getYourTotalScore().getText())));
                FRC.getWhoWinLabel().setText("YOU LOSE!");
                FRC.getWinnerMsgLabel().setText("BETTER LUCK NEXT\nTIME!");
            }

            ControllerUtil.changeScenes(ControllerUtil.getFinalResultsScene());
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
