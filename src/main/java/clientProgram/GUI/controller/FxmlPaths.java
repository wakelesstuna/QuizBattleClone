package clientProgram.GUI.controller;

public enum FxmlPaths {

    LOGIN_MENU("view/loginMenu.fxml"),
    GAME_MENU("view/gameMenu.fxml"),
    RANDOM_PLAYER("view/randomPlayer.fxml"),
    SEARCHING_FOR_PLAYER("view/SearchingForPlayer.fxml"),
    GAME_BOARD("view/gameBoard.fxml"),
    CATEGORY_BOARD("view/categoryChoiceBoard.fxml"),
    QUESTION_BOARD("view/questionBoard.fxml"),
    FINAL_RESULTS("view/finalResults.fxml");

    private final String s;

    FxmlPaths(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
