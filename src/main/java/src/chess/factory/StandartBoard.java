package src.chess.factory;

import src.chess.model.pieces.*;

public class StandartBoard extends Board {

    public StandartBoard() {

        super();

    }

    /**
     * Board made with the real Pieces and position
     */
    @Override
    public void BoardInitialize() {

        this.setPiece(0, 0, new Rook(Color.BLACK));
        this.setPiece(0, 1, new Knight(Color.BLACK));
        this.setPiece(0, 2, new Bishop(Color.BLACK));
        this.setPiece(0, 3, new Queen(Color.BLACK));
        this.setPiece(0, 4, new King(Color.BLACK));
        this.setPiece(0, 5, new Bishop(Color.BLACK));
        this.setPiece(0, 6, new Knight(Color.BLACK));
        this.setPiece(0, 7, new Rook(Color.BLACK));
        for (int j = 0; j < 8; j++) {
            this.setPiece(1, j, new Pawn(Color.BLACK));
        }

        this.setPiece(7, 0, new Rook(Color.WHITE));
        this.setPiece(7, 1, new Knight(Color.WHITE));
        this.setPiece(7, 2, new Bishop(Color.WHITE));
        this.setPiece(7, 3, new Queen(Color.WHITE));
        this.setPiece(7, 4, new King(Color.WHITE));
        this.setPiece(7, 5, new Bishop(Color.WHITE));
        this.setPiece(7, 6, new Knight(Color.WHITE));
        this.setPiece(7, 7, new Rook(Color.WHITE));
        for (int j = 0; j < 8; j++) {
            this.setPiece(6, j, new Pawn(Color.WHITE));
        }

    }

}
