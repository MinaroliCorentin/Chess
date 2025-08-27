package src.chess.GameManagement;


import javafx.scene.control.Alert;
import src.chess.customalert.MultiProposeAlert;
import src.chess.factory.Board;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.Player;
import src.chess.gamestatus.GameStatus;
import src.chess.gamestatus.GameStatusFx;
import src.chess.status.PiecesStatus;

public class GameManagementFx extends GameManagement {

    public GameManagementFx(Board board, Player white, Player black) {
        super(board,white,black);
    }

    public void playMove(String from, String to) {

        GameStatus gameStatusFx = new GameStatusFx(getBoard());
        PiecesStatus piecesStatus = new PiecesStatus(getBoard());

        try {
            System.out.println(getPlayerNameBasedOnRound());
            if ((getRounds()) % 2 == 0) {
                // White
                if (piecesStatus.stalemate(getBoard(), PiecesColor.WHITE)) {
                    MultiProposeAlert multiProposeAlert = new MultiProposeAlert(Alert.AlertType.INFORMATION);
                    multiProposeAlert.showMessageWithTimeout("Stalemate ! Black Wins", 10);
                }
                getWhite().play(from, to);
                if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.WHITE)) {
                    MultiProposeAlert multiProposeAlert = new MultiProposeAlert(Alert.AlertType.INFORMATION);
                    multiProposeAlert.showMessageWithTimeout(" WhiteKing in check", 3);
                }
            } else {
                // Black
                if (piecesStatus.stalemate(getBoard(), PiecesColor.BLACK)) {
                    MultiProposeAlert multiProposeAlert = new MultiProposeAlert(Alert.AlertType.INFORMATION);
                    multiProposeAlert.showMessageWithTimeout("Stalemate ! Black Wins", 10);
                }
                getBlack().play(from, to);
                if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.BLACK)) {
                    MultiProposeAlert multiProposeAlert = new MultiProposeAlert(Alert.AlertType.INFORMATION);
                    multiProposeAlert.showMessageWithTimeout(" BlackKing in check", 3);
                }
            }

            gameStatusFx.promoting();
            setRounds(getRounds() + 1);
            gameStatusFx.setDrawCounter(getRounds() + 1);
            if (this.isGameOver()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("End of the game");
                alert.setHeaderText(null);
                alert.setContentText("This match is over");
                alert.showAndWait();
            }

        } catch (RuntimeException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }


    public boolean isGameOver() {
        GameStatus gameStatusFx = new GameStatusFx(getBoard());
        return gameStatusFx.isCheckmate(PiecesColor.WHITE)
                || gameStatusFx.isCheckmate(PiecesColor.BLACK)
                || gameStatusFx.isDraw();
    }
}