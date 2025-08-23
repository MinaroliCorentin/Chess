package src.chess.model.Fx;


import javafx.scene.control.Alert;
import src.chess.factory.Board;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.Player;
import src.chess.status.GameStatus;
import src.chess.status.PiecesStatus;

import java.util.Random;

public class GameManagementFx {

    private Board board;
    private Player white;
    private Player black;
    private int rounds;
    private int startOffset;

    public GameManagementFx(Board board, Player white, Player black) {
        this.board = board;
        this.white = white;
        this.black = black;
        this.rounds = 0;

        Random random = new Random();
        this.startOffset = random.nextInt(2);
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getPlayerNameBasedOnRound() {
        return ((rounds + startOffset) % 2 == 0) ? "WHITE" : "BLACK";
    }


    public void playMove(String from, String to) {
        GameStatus gameStatus = new GameStatus(board);
        PiecesStatus piecesStatus = new PiecesStatus(board);

        try {
            System.out.println(getPlayerNameBasedOnRound());
            if ((rounds + startOffset) % 2 == 0) {
                // White
                if (piecesStatus.stalemate(board, PiecesColor.WHITE)) {
                    System.out.println("Stalemate Black Wins");
                }
                white.play(from, to);
                if (piecesStatus.isKingInCheck(board, PiecesColor.WHITE)) {
                    System.out.println("White King in check");
                }
            } else {
                // Black
                if (piecesStatus.stalemate(board, PiecesColor.BLACK)) {
                    System.out.println("Stalemate White Wins");
                }
                black.play(from, to);
                if (piecesStatus.isKingInCheck(board, PiecesColor.BLACK)) {
                    System.out.println("Black King in check");
                }
            }

            gameStatus.promoting();
            setRounds(getRounds() + 1);
            gameStatus.setDrawCounter(getRounds() + 1);
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
        GameStatus gameStatus = new GameStatus(board);
        return gameStatus.isCheckmate(PiecesColor.WHITE)
                || gameStatus.isCheckmate(PiecesColor.BLACK)
                || gameStatus.isDraw();
    }
}