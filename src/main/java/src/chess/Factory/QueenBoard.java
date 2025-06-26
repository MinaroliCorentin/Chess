package src.chess.Factory;

import src.chess.Model.pieces.Color;
import src.chess.Model.pieces.Queen;

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
