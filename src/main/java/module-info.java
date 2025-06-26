module src.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires junit;
    requires org.testng;

    opens src.chess to javafx.fxml;
    exports src.chess;
    exports src.chess.Controller;
    exports src.chess.Test;
    opens src.chess.Controller to javafx.fxml;
}