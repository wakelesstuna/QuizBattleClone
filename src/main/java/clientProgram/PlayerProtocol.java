package clientProgram;

import clientProgram.GUI.FxmlUtil;
import clientProgram.GUI.controllers.FinalResultsController;
import clientProgram.GUI.controllers.GameBoardController;
import clientProgram.GUI.controllers.QuestionBoardController;
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
                case CHANGE_SCENE -> changeScene((InfoObj)objFromServer);
                case READY_TO_PLAY -> System.out.println(((InfoObj) objFromServer).getMsg());
                case ASK_CATEGORY -> askForcategory((InfoObj) objFromServer);
                case SEND_QUESTION -> Main.question = (Question) objFromServer;
                case GAME_OVER -> finalScore();
            }

        }else if (objFromServer instanceof Question){
            System.out.println("Question here");
            Platform.runLater(() -> {

                FxmlUtil.getQuestionBoardController().getCategoryLabel().setText("Java");

                FxmlUtil.getGameBoardController().playButton.setDisable(false);
                FxmlUtil.getQuestionBoardController().getAnswerButtonsList().forEach(button -> {button.setDisable(false); button.setStyle("-fx-background-color: #D1FDFF");});

                FxmlUtil.getQuestionBoardController().setQuestion(((Question) objFromServer));
                FxmlUtil.getQuestionBoardController().getCategoryLabel().setText(((Question) objFromServer).getCategoryName());
                FxmlUtil.getQuestionBoardController().getQuestionField().setText(((Question) objFromServer).getQuestion());

                for (int i = 0; i < FxmlUtil.getQuestionBoardController().getAnswerButtonsList().size(); i++) {
                    FxmlUtil.getQuestionBoardController().getAnswerButtonsList()
                            .get(i).setText(((Question) objFromServer).getAnswerChoices().get(i));
                }

                FxmlUtil.getQuestionBoardController().getWaitingIndicator().setVisible(false);
                FxmlUtil.getQuestionBoardController().getWaitingLabel().setVisible(false);

                FxmlUtil.changeScenes(FxmlUtil.getQuestionBoardScene());
            });

        }else if(objFromServer instanceof StartPackage){
            GameBoardController.numberOfRounds = ((StartPackage) objFromServer).getGameRounds();
            GameBoardController.numberOfQuestions = ((StartPackage) objFromServer).getQuestionPerRounds();
        }
    }

    private void changeScene(InfoObj objFromServer) {
        GameBoardController GBC = FxmlUtil.getGameBoardController();
        QuestionBoardController QBC = FxmlUtil.getQuestionBoardController();
        System.out.println("dags att byta scene");
        System.out.println(objFromServer.getPlayer());
        System.out.println(objFromServer.getOpponent());
        //System.out.println(objFromServer.getPlayer().getPlayerName());
        //System.out.println("" + objFromServer.getPlayer().getPlayerRoundScore());

            Platform.runLater(() -> {
                FxmlUtil.getGameBoardController().getWithRoundNumberLabel().setText("" + FxmlUtil.getGameBoardController().getWhichRoundNumber());
               // FxmlUtil.getGameBoardController().getOpponentName().setText(objFromServer.getPlayer().getPlayerName());
                FxmlUtil.getGameBoardController().getOpponentRound1Score().setText("" + (objFromServer.getRoundScore()));

                if (QBC.getRounds() == 1){
                    GBC.getOpponentRound1Score().setText("" + objFromServer.getRoundScore());
                    GBC.getOpponentTotalScore().setText("" + objFromServer.getRoundScore());
                }else if (QBC.getRounds() == 2){
                    GBC.getOpponentTotalScore().setText(String.valueOf(Integer.parseInt(GBC.getOpponentTotalScore().getText()) + objFromServer.getRoundScore()));
                    GBC.getOpponentRound2Score().setText(String.valueOf(objFromServer.getRoundScore()));
                }else if (QBC.getRounds() == 3){
                    GBC.getOpponentTotalScore().setText(String.valueOf(Integer.parseInt(GBC.getOpponentTotalScore().getText()) + objFromServer.getRoundScore()));
                    GBC.getOpponentRound3Score().setText("" + objFromServer.getRoundScore());
                }else if (QBC.getRounds() == 4){
                    GBC.getOpponentTotalScore().setText(String.valueOf(Integer.parseInt(GBC.getOpponentTotalScore().getText()) + objFromServer.getRoundScore()));
                    GBC.getOpponentRound4Score().setText("" + objFromServer.getRoundScore());
                }else{
                    GBC.getOpponentTotalScore().setText(String.valueOf(Integer.parseInt(GBC.getOpponentTotalScore().getText()) + objFromServer.getRoundScore()));
                    GBC.getOpponentRound5Score().setText("" + objFromServer.getRoundScore());
                }
                FxmlUtil.changeScenes(FxmlUtil.getGameBoardScene());
            });

    }

    public void finalScore(){
        Platform.runLater(()-> {
            FinalResultsController FRC = FxmlUtil.getFinalResultsController();
            GameBoardController GBC = FxmlUtil.getGameBoardController();

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

            FxmlUtil.changeScenes(FxmlUtil.getFinalResultsScene());
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
