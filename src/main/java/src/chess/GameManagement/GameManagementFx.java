package src.chess.GameManagement;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import src.chess.customalert.MultiProposeAlert;
import src.chess.customalert.PlayerTurnAlert;
import src.chess.factory.Board;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.Player;
import src.chess.gamestatus.GameStatus;
import src.chess.gamestatus.GameStatusFx;
import src.chess.model.pieces.PiecesStatus;

public class GameManagementFx extends GameManagement {

    public GameManagementFx(Board board, Player white, Player black) {
        super(board,white,black);
    }

    public Player getPlayerBaseOnRound() {
        return getRounds() % 2 == 0 ? getBlack() : getWhite();
    }

    public void playMove(String from, String to) {

        GameStatus gameStatusFx = new GameStatusFx(getBoard());
        PiecesStatus piecesStatus = new PiecesStatus(getBoard());
        PlayerTurnAlert playerTurnALert = new PlayerTurnAlert(Alert.AlertType.INFORMATION, "player turn", getPlayerBaseOnRound());
        MultiProposeAlert multiProposeAlert = new MultiProposeAlert(Alert.AlertType.WARNING);

            try {
            playerTurnALert.showWithTimeout(1);
            if ((getRounds()) % 2 == 0) {
                // White
                if (piecesStatus.stalemate(getBoard(), PiecesColor.WHITE)) {
                    multiProposeAlert.showMessageWithTimeout("Stalemate ! Black Wins", 10);
                    Platform.exit();
                }

                getWhite().play(from, to);

                if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.BLACK)) {
                    multiProposeAlert.showMessageWithTimeout(" BlackKing in check", 3);
                }
            } else {
                // Black
                if (piecesStatus.stalemate(getBoard(), PiecesColor.BLACK)) {
                    multiProposeAlert.showMessageWithTimeout("Stalemate ! White Wins", 10);
                    Platform.exit();
                }

                getBlack().play(from, to);

                if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.WHITE)) {
                    multiProposeAlert.showMessageWithTimeout(" WhiteKing in check", 3);
                }
            }

            gameStatusFx.promoting();
            setRounds(getRounds() + 1);
            gameStatusFx.setDrawCounter(getRounds() + 1);
            if (this.isGameOver()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("End of the game");
                alert.setHeaderText(null);
                alert.setContentText( this.getPlayerNameBasedOnRound() + "Wins ! ");
                alert.showAndWait();
                Platform.exit();
            }

        } catch (RuntimeException e) {

            multiProposeAlert.showMessageWithTimeout(e.getMessage(), 2);
        }
    }


    public boolean isGameOver() {
        GameStatus gameStatusFx = new GameStatusFx(getBoard());
        return gameStatusFx.isCheckmate(PiecesColor.WHITE)
                || gameStatusFx.isCheckmate(PiecesColor.BLACK)
                || gameStatusFx.isDraw();
    }
}