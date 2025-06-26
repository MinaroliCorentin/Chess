package src.chess.Factory;

import src.chess.Model.pieces.Bishop;
import src.chess.Model.pieces.Color;

public class BishopBoard extends Board{

    public BishopBoard(){
        super();
    }

    @Override
    public void BoardInitialize(){

        this.setPiece(7, 2, new Bishop(Color.WHITE));
        this.setPiece(0, 2, new Bishop(Color.BLACK));

        this.setPiece(7, 5, new Bishop(Color.WHITE));
        this.setPiece(0, 5, new Bishop(Color.BLACK));

    }

}
