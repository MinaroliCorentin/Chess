package src.chess.customalert;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class MultiProposeAlert extends Alert {

    public MultiProposeAlert(AlertType alertType){
        super(alertType);
    }

    /**
     * Create a popup that disappear after X seconds
     * @param message The content of the Message
     * @param seconds Time to live of the Popup
     */
    public void showMessageWithTimeout(String message, int seconds){

        setContentText(message);
        setTitle(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), e -> this.close()));
        timeline.setCycleCount(1);
        timeline.play();
        show();

    }

    public void showGameOverAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of the game");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        Platform.exit();
    }

}
