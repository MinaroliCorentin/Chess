package src.chess.model.handler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import src.chess.factory.Board;
import src.chess.model.pieces.*;

import java.util.Optional;

public class PromoteHandlerFx {

    private Board board ;

    public PromoteHandlerFx(Board board) {
        this.board = board ;

    }

    public void promotion(int x, int y, PiecesColor piecesColor){

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Rook", "Knight", "Queen", "Bishop");
        dialog.setTitle("Pawn Promotion");
        dialog.setHeaderText("A pawn promotion has been detected!");
        dialog.setContentText("Please select your new piece:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            switch (choice) {
                case "Rook" -> board.setPiece(x, y, new Rook(piecesColor));
                case "Knight" -> board.setPiece(x, y, new Knight(piecesColor));
                case "Bishop" -> board.setPiece(x, y, new Bishop(piecesColor));
                case "Queen" -> board.setPiece(x, y, new Queen(piecesColor));
            }
        });


    }

}
