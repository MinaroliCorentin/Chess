package src.chess.factory;

import src.chess.model.pieces.Bishop;
import src.chess.model.pieces.Color;

public class BishopBoard extends Board{

    public BishopBoard(){
        super();
    }

    /**
     * Create a board made to test the bishop
     */
    @Override
    public void BoardInitialize(){



        this.setPiece(7, 2, new Bishop(Color.WHITE));
        this.setPiece(0, 2, new Bishop(Color.BLACK));

        this.setPiece(7, 5, new Bishop(Color.WHITE));
        this.setPiece(0, 5, new Bishop(Color.BLACK));

    }

}
