package src.chess.factory;

import src.chess.model.pieces.PiecesColor;
import src.chess.model.pieces.King;

public class KingBoard extends Board{

    public KingBoard(){
        super();
    }

    /**
     * Board made to test King
     */
    @Override
    public void BoardInitialize(){


        this.setPiece(0, 4, new King(PiecesColor.BLACK));
        this.setPiece(7, 4, new King(PiecesColor.WHITE));


    }

}
