package src.chess.factory;

import src.chess.model.pieces.PiecesColor;
import src.chess.model.pieces.Pawn;

public class PawnBoard extends Board{

    public PawnBoard(){
        super();
    }

    /**
     * Board made to test Pawn
     */
    @Override
    public void BoardInitialize(){

        this.setPiece(6, 0, new Pawn(PiecesColor.WHITE));
        this.setPiece(1, 7, new Pawn(PiecesColor.BLACK));

    }

}