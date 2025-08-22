package src.chess.factory;

import src.chess.model.pieces.PiecesColor;
import src.chess.model.pieces.Rook;

public class RookBoard extends Board{

    public RookBoard(){
        super();
    }

    /**
     * Board made to test Rook
     */
    @Override
    public void BoardInitialize(){

        this.setPiece(0, 0, new Rook(PiecesColor.BLACK));
        this.setPiece(7, 0, new Rook(PiecesColor.WHITE));
        this.setPiece(0, 7, new Rook(PiecesColor.BLACK));
        this.setPiece(7, 7, new Rook(PiecesColor.WHITE));

    }

}