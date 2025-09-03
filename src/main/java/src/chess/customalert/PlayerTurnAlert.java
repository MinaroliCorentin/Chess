package src.chess.customalert;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import src.chess.model.players.Player;

public class PlayerTurnAlert extends Alert {

    public PlayerTurnAlert(AlertType alertType, String title, Player player) {

        super(alertType);
        setTitle(title);
        setHeaderText(null);
        setContentText("It's " + player.getPlayerName() + " turn");
    }

    public void showWithTimeout(int seconds) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), e -> this.close()));
        timeline.setCycleCount(1);
        timeline.play();
        show();
    }
}