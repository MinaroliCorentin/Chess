package src.chess.gamemanagement;


import javafx.application.Platform;
import javafx.scene.control.Alert;
import src.chess.customalert.MultiProposeAlert;
import src.chess.factory.Board;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.Player;
import src.chess.gamestatus.GameStatus;
import src.chess.gamestatus.GameStatusFx;
import src.chess.model.pieces.PiecesStatus;

public class GameManagementFx extends GameManagement {

    public GameManagementFx(Board board, Player white, Player black,GameStatus gameStatus) {
        super(board,white,black,gameStatus);
    }

    /**
     * Based on the rounds, choose who gonna play
     * @param from Starting Piece position
     * @param to Ending Piece position
     */
    public void playMove(String from, String to) {

        PiecesStatus piecesStatus = new PiecesStatus(getBoard());
        MultiProposeAlert multiProposeAlert = new MultiProposeAlert(Alert.AlertType.WARNING);

            try {
            if ((getRounds()) % 2 == 0) {
                // White
                if (piecesStatus.stalemate(getBoard(), PiecesColor.WHITE)) {
                    multiProposeAlert.showGameOverAlert("Stalemate ! It's a draw !");
                    return;
                }

                getWhite().play(from, to);

                if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.BLACK)) {
                    multiProposeAlert.showMessageWithTimeout(" BlackKing in check", 3);
                }
            } else {
                // Black
                if (piecesStatus.stalemate(getBoard(), PiecesColor.BLACK)) {
                    multiProposeAlert.showGameOverAlert("Stalemate ! It's a draw");
                    return;
                }

                getBlack().play(from, to);

                if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.WHITE)) {
                    multiProposeAlert.showMessageWithTimeout(" WhiteKing in check", 3);
                }
            }

            getGameStatus().promoting();

            if ( this.isDraw()){
                multiProposeAlert.showGameOverAlert("It's a draw !");
            }

            if (this.isGameOver(this.getPlayerBaseOnRoundReversed().getColor())) {
                String a = getPlayerBaseOnRound().getColor().toString().toLowerCase() ;
                String b = a.substring(0,1).toUpperCase();
                multiProposeAlert.showGameOverAlert( b + a.substring(1) + " Wins");
                return;
            }
            setRounds(getRounds() + 1);
            getGameStatus().inscreaseDrawCounter();

        } catch (RuntimeException e) {

            multiProposeAlert.showMessageWithTimeout(e.getMessage(), 2);
        }
    }

}