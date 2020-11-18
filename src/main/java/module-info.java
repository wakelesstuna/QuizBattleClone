module BestQuizBattleEver {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens ClientProgram;
    opens ClientProgram.GUI;
    opens ClientProgram.GUI.controller;
    opens view;
    opens images;
    opens images.advertising;
}