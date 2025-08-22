package src.chess.factory;

import src.chess.model.pieces.Bishop;
import src.chess.model.pieces.PiecesColor;

public class BishopBoard extends Board{

    public BishopBoard(){
        super();
    }

    /**
     * Create a board made to test the bishop
     */
    @Override
    public void BoardInitialize(){



        this.setPiece(7, 2, new Bishop(PiecesColor.WHITE));
        this.setPiece(0, 2, new Bishop(PiecesColor.BLACK));

        this.setPiece(7, 5, new Bishop(PiecesColor.WHITE));
        this.setPiece(0, 5, new Bishop(PiecesColor.BLACK));

    }

}
