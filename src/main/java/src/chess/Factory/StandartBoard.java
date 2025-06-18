package src.chess.Factory;

import src.chess.Model.pieces.*;

public class StandartBoard extends Board {

    public StandartBoard() {

        super();

    }

    @Override
    public void BoardInitialize() {

        this.setPiece(0, 0, new Rook(Color.BLACK));
        this.setPiece(1, 0, new Knight(Color.BLACK));
        this.setPiece(2, 0, new Bishop(Color.BLACK));
        this.setPiece(3, 0, new Queen(Color.BLACK));
        this.setPiece(4, 0, new King(Color.BLACK));
        this.setPiece(5, 0, new Bishop(Color.BLACK));
        this.setPiece(6, 0, new Knight(Color.BLACK));
        this.setPiece(7, 0, new Rook(Color.BLACK));
        for (int j = 0; j < 8; j++) {
            this.setPiece(j, 1, new Pawn(Color.BLACK));
        }

        this.setPiece(0, 7, new Rook(Color.WHITE));
        this.setPiece(1, 7, new Knight(Color.WHITE));
        this.setPiece(2, 7, new Bishop(Color.WHITE));
        this.setPiece(3, 7, new Queen(Color.WHITE));
        this.setPiece(4, 7, new King(Color.WHITE));
        this.setPiece(5, 7, new Bishop(Color.WHITE));
        this.setPiece(6, 7, new Knight(Color.WHITE));
        this.setPiece(7, 7, new Rook(Color.WHITE));
        for (int j = 0; j < 8; j++) {
            this.setPiece(j, 6, new Pawn(Color.WHITE));
        }

    }

}
