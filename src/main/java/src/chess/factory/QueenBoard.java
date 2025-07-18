package src.chess.factory;

import src.chess.model.pieces.Color;
import src.chess.model.pieces.Queen;

public class QueenBoard extends Board{

    public QueenBoard(){
        super();
    }

    @Override
    public void BoardInitialize(){

        this.setPiece(7, 3, new Queen(Color.WHITE));
        this.setPiece(0, 3, new Queen(Color.BLACK));


    }

}
