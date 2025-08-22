package src.chess.factory;

import src.chess.model.pieces.PiecesColor;
import src.chess.model.pieces.King;
import src.chess.model.pieces.Rook;

public class CastlingBoard extends Board{

    public CastlingBoard(){
        super();
    }

    /**
     * Board made to test Castling
     */
    @Override
    public void BoardInitialize(){

        this.setPiece(0, 0, new Rook(PiecesColor.BLACK));
        this.setPiece(7, 0, new Rook(PiecesColor.WHITE));
        this.setPiece(0, 7, new Rook(PiecesColor.BLACK));
        this.setPiece(7, 7, new Rook(PiecesColor.WHITE));
        this.setPiece(0,4, new King(PiecesColor.BLACK));
        this.setPiece(7,4, new King(PiecesColor.WHITE));
    }

}
