package src.chess.factory;

import src.chess.model.pieces.Color;
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

        this.setPiece(0, 0, new Rook(Color.BLACK));
        this.setPiece(7, 0, new Rook(Color.WHITE));
        this.setPiece(0, 7, new Rook(Color.BLACK));
        this.setPiece(7, 7, new Rook(Color.WHITE));

    }

}