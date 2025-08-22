package src.chess.factory;

import src.chess.model.pieces.PiecesColor;
import src.chess.model.pieces.Queen;

public class QueenBoard extends Board{

    public QueenBoard(){
        super();
    }

    /**
     * Board made to test queen
     */
    @Override
    public void BoardInitialize(){

        this.setPiece(7, 3, new Queen(PiecesColor.WHITE));
        this.setPiece(0, 3, new Queen(PiecesColor.BLACK));


    }

}
