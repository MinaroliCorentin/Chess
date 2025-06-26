package src.chess.Factory;

import src.chess.Model.pieces.Color;
import src.chess.Model.pieces.King;

public class KingBoard extends Board{

    public KingBoard(){
        super();
    }

    @Override
    public void BoardInitialize(){


        this.setPiece(0, 4, new King(Color.BLACK));
        this.setPiece(7, 4, new King(Color.WHITE));


    }

}
