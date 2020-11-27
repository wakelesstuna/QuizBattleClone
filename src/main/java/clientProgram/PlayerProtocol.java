package clientProgram;

import clientProgram.GUI.FxmlUtil;
import clientProgram.GUI.controllers.CategoryChoiceBoardController;
import clientProgram.GUI.controllers.FinalResultsController;
import clientProgram.GUI.controllers.GameBoardController;
import clientProgram.GUI.controllers.QuestionBoardController;
import javafx.scene.control.Label;
import model.InfoObj;
import model.Player;
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

    public void checkObjectFromServer(Object objFromServer) {

        if (objFromServer instanceof InfoObj) {

            switch (((InfoObj) objFromServer).getState()) {
                case READY_TO_PLAY -> System.out.println(((InfoObj) objFromServer).getMsg());
                case GO_TO_GAMEBOARD -> loadGameBoard((InfoObj) objFromServer);
                case ASK_CATEGORY -> askForCategory((InfoObj) objFromServer);
                case GAME_OVER -> finalScore((InfoObj) objFromServer);
            }

        } else if (objFromServer instanceof Question) {
            QuestionBoardController QBC = FxmlUtil.getQuestionBoardController();
            CategoryChoiceBoardController CCBC = FxmlUtil.getCategoryChoiceBoardController();
            switch (((Question) objFromServer).getCategoryName()) {
                case "sports" -> CCBC.getSports().setDisable(true);
                case "scienceAndNature" -> CCBC.getScienceAndNature().setDisable(true);
                case "animals" -> CCBC.getAnimals().setDisable(true);
                case "geography" -> CCBC.getGeography().setDisable(true);
                case "history" -> CCBC.getHistory().setDisable(true);
            }

            Platform.runLater(() -> {

                FxmlUtil.getGameBoardController().playButton.setDisable(false);
                QBC.getAnswerButtonsList().forEach(button -> {
                    button.setDisable(false);
                    button.setStyle("-fx-background-color: #D1FDFF");
                });

                QBC.setQuestion(((Question) objFromServer));
                QBC.getCategoryLabel().setText(((Question) objFromServer).getCategoryName());
                QBC.getQuestionField().setText(((Question) objFromServer).getQuestion());

                for (int i = 0; i < QBC.getAnswerButtonsList().size(); i++) {
                    QBC.getAnswerButtonsList().get(i)
                            .setText(((Question) objFromServer).getAnswerChoices().get(i));
                }

                QBC.getWaitingIndicator().setVisible(false);
                QBC.getWaitingLabel().setVisible(false);

                FxmlUtil.changeScenes(FxmlUtil.getQuestionBoardScene());
            });

        } else if (objFromServer instanceof StartPackage) {
            GameBoardController GBC = FxmlUtil.getGameBoardController();
            GBC.setNumberOfRounds(((StartPackage) objFromServer).getGameRounds());
            GBC.setNumberOfQuestions(((StartPackage) objFromServer).getQuestionPerRounds());

            if (GBC.getNumberOfRounds() == 2) {
                GBC.getTwoRoundPane().setVisible(true);
            } else if (GBC.getNumberOfRounds() == 3) {
                GBC.getThreeRoundPane().setVisible(true);
            } else {
                GBC.getFourRoundPane().setVisible(true);
            }
        }
    }

    private void loadGameBoard(InfoObj objFromServer) {
        GameBoardController GBC = FxmlUtil.getGameBoardController();

        Label opponentTotalScoreLabel = GBC.getOpponentTotalScore();
        int currentOpponentTotalScore = Integer.parseInt(GBC.getOpponentTotalScore().getText());
        int tempRoundScore = objFromServer.getOpponent().getPlayerRoundScore();

        Platform.runLater(() -> {
            GBC.getWithRoundNumberLabel().setText("" + FxmlUtil.getGameBoardController().getWhichRoundNumber());
            GBC.getOpponentName().setText(objFromServer.getOpponent().getPlayerName());
            GBC.getOpponentRound1Score().setText("" + (objFromServer.getOpponent().getPlayerRoundScore()));

            if (GBC.getWhichRoundNumber() == 1) {
                GBC.getOpponentRound1Score().setText(String.valueOf(tempRoundScore));
            } else if (GBC.getWhichRoundNumber() == 2) {
                GBC.getOpponentRound2Score().setText(String.valueOf(tempRoundScore));
            } else if (GBC.getWhichRoundNumber() == 3) {
                GBC.getOpponentRound3Score().setText(String.valueOf(tempRoundScore));
            } else if (GBC.getWhichRoundNumber() == 4) {
                GBC.getOpponentRound4Score().setText(String.valueOf(tempRoundScore));
            } else {
                GBC.getOpponentRound5Score().setText(String.valueOf(tempRoundScore));
            }
            opponentTotalScoreLabel.setText(String.valueOf(currentOpponentTotalScore + tempRoundScore));
            FxmlUtil.changeScenes(FxmlUtil.getGameBoardScene());
        });

    }

    private void askForCategory(InfoObj infoObj) {
        Platform.runLater(() -> {
            CategoryChoiceBoardController CCBC = FxmlUtil.getCategoryChoiceBoardController();
            System.out.println("inne i askForCategory");
            System.out.println(infoObj.getMsg());
            System.out.println(Main.playerName);
            if (infoObj.getMsg().equals(Main.playerName)) {
                CCBC.getWhosTurnLabel().setText("Your turn");
                CCBC.getChoseACategoryLabel().setText("Choose a category");
                CCBC.getGeography().setDisable(false);
                CCBC.getHistory().setDisable(false);
                CCBC.getAnimals().setDisable(false);
                CCBC.getScienceAndNature().setDisable(false);
                CCBC.getSports().setDisable(false);
                CCBC.getWaitingIndicator().setVisible(false);
            } else {
                CCBC.getWhosTurnLabel().setText("Opponent turn");
                CCBC.getChoseACategoryLabel().setText("waiting for opponent to choose category");
                CCBC.getGeography().setDisable(true);
                CCBC.getHistory().setDisable(true);
                CCBC.getAnimals().setDisable(true);
                CCBC.getScienceAndNature().setDisable(true);
                CCBC.getSports().setDisable(true);
                CCBC.getWaitingIndicator().setVisible(true);

                // TODO: 2020-11-26 fixa score så de blir rätt runda 1 och ta bort choise när man valt categori 
            }
            FxmlUtil.changeScenes(FxmlUtil.getCategoryChoiceBoardScene());
        });
    }

    public void finalScore(InfoObj objFromServer) {
        Platform.runLater(() -> {
            FinalResultsController FRC = FxmlUtil.getFinalResultsController();
            GameBoardController GBC = FxmlUtil.getGameBoardController();
            int yourTotalScore = Integer.parseInt(GBC.getYourTotalScore().getText());
            int opponentTotalScore = objFromServer.getOpponent().getPlayerTotalScore();

            if (yourTotalScore > opponentTotalScore) {
                FRC.getYouFinalScore().setText(String.valueOf(yourTotalScore));
                FRC.getWhoWinLabel().setText("YOU WIN!");
                FRC.getWinnerMsgLabel().setText("CONGRATULATIONS!");
            } else if (yourTotalScore == opponentTotalScore) {
                FRC.getYouFinalScore().setText(String.valueOf(yourTotalScore));
                FRC.getWhoWinLabel().setText("IT'S A TIE");
                FRC.getWinnerMsgLabel().setText("BETTER LUCK\nNEXT TIME!");
            } else {
                FRC.getYouFinalScore().setText(String.valueOf(yourTotalScore));
                FRC.getWhoWinLabel().setText("YOU LOSE!");
                FRC.getWinnerMsgLabel().setText("BETTER LUCK NEXT\nTIME!");
            }
            FRC.getOpponentFinalScore().setText(String.valueOf(opponentTotalScore));
            FRC.getOpponentName().setText(objFromServer.getOpponent().getPlayerName());

            FxmlUtil.changeScenes(FxmlUtil.getFinalResultsScene());
        });
    }

}
