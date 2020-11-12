module BestQuizBattleEver {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    // är de tomma package adda ej!
    opens ClientProgram;
    opens ClientProgram.GUI;
    opens ClientProgram.GUI.controller;
    opens view;
    opens images;
}