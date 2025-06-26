package src.chess.Factory;

import src.chess.Model.pieces.Color;
import src.chess.Model.pieces.Rook;

public class RookBoard extends Board{

    public RookBoard(){
        super();
    }

    @Override
    public void BoardInitialize(){

        this.setPiece(0, 0, new Rook(Color.BLACK));
        this.setPiece(7, 0, new Rook(Color.WHITE));


    }

}
