module src.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens src.chess to javafx.fxml;
    exports src.chess;
    exports src.chess.Controller;
    opens src.chess.Controller to javafx.fxml;
}