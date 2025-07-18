package src.chess.factory;

import src.chess.model.pieces.Color;
import src.chess.model.pieces.King;

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
