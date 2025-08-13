package src.chess.factory;

import src.chess.model.pieces.Bishop;
import src.chess.model.pieces.Color;
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

        this.setPiece(6, 0, new Pawn(Color.WHITE));
        this.setPiece(1, 7, new Pawn(Color.BLACK));

    }

}