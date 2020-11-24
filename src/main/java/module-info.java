module BestQuizBattleEver {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens clientProgram;
    opens clientProgram.GUI;
    opens clientProgram.GUI.controller;
    opens view;
    opens images;
    opens images.advertising;
}