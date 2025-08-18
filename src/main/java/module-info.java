module src.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires junit;
    requires org.testng;

    opens src.chess to javafx.fxml;
    exports src.chess;
    exports src.chess.controller;
    exports src.chess.test;
    opens src.chess.controller to javafx.fxml;
    exports src.chess.game;
    opens src.chess.game to javafx.fxml;
}