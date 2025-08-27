package src.chess.gamestatus;

import src.chess.factory.Board;
import src.chess.model.handler.PromoteHandlerFx;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.PiecesColor;

import java.util.Map;

public class GameStatusFx extends GameStatus {

    public GameStatusFx(Board board) {

        super(board);

    }

    /**
     * Verify if a Pawn is located the Top or Bottom of the map depending of his color.
     * Then promoteHandler to promote the pawn.
     */
    @Override
    public void promoting() {
        PromoteHandlerFx promoteHandlerFx = new PromoteHandlerFx(getBoard());
        Map<Localisation, Pieces> allPieces = getBoard().getPiecesMap();

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            if (piece.isPawn()) {
                Localisation loc = entry.getKey();

                if (piece.getColor() == PiecesColor.WHITE && loc.getX() == 0) {
                    getBoard().setPiece(loc.getX(), loc.getY(), null);
                    promoteHandlerFx.promotion(loc.getX(), loc.getY(), PiecesColor.WHITE);
                } else if (piece.getColor() == PiecesColor.BLACK && loc.getX() == 7) {
                    getBoard().setPiece(loc.getX(), loc.getY(), null);
                    promoteHandlerFx.promotion(loc.getX(), loc.getY(), PiecesColor.BLACK);
                }
            }
        }
    }


}
