module src.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires junit;
    requires org.testng;
    requires javafx.graphics;
    requires javafx.base;

    opens src.chess to javafx.fxml;
    exports src.chess;
    exports src.chess.controller;
    exports src.chess.test;
    opens src.chess.controller to javafx.fxml;
    exports src.chess.game;
    opens src.chess.game to javafx.fxml;
    exports src.chess.GameManagement;
    opens src.chess.GameManagement to javafx.fxml;
}