package src.chess.test;

import org.junit.Before;
import org.junit.Test;
import src.chess.factory.*;
import src.chess.model.pieces.*;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;
import src.chess.status.PiecesStatus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PiecesStatusTest {

    private Board bishopBoard;
    private Board kingBoard;
    private Board rookBoard;
    private Board board;
    private Board queenBoard;
    private CastlingBoard castlingBoard;
    private EmptyBoard emptyBoard;

    private Player playerBlackBishop;
    private Player playerWhiteBishop;
    private Player playerBlackKing;
    private Player playerWhiteKing;
    private Player playerBlackRook;
    private Player playerWhiteRook;
    private Player playerBlack;
    private Player playerWhite;
    private Player playerBlackQueen;
    private Player playerWhiteQueen;
    private Player playerBlackCastling ;
    private Player playerWhiteCastling;
    private Player playerWhiteEmpty;
    private Player playerBlackEmpty;

    String from ;
    String to;

    @Before
    public void setUp() {

        board = new StandartBoard();
        playerBlack = new HumanPlayer(board, PiecesColor.BLACK,"Black");
        playerWhite = new HumanPlayer(board, PiecesColor.WHITE,"White");

        bishopBoard = new BishopBoard();
        playerBlackBishop = new HumanPlayer(bishopBoard, PiecesColor.BLACK,"Black");
        playerWhiteBishop = new HumanPlayer(bishopBoard, PiecesColor.WHITE,"White");

        castlingBoard = new CastlingBoard();
        playerBlackCastling = new HumanPlayer(castlingBoard, PiecesColor.BLACK,"Black");
        playerWhiteCastling = new HumanPlayer(castlingBoard, PiecesColor.WHITE,"White");

        kingBoard = new KingBoard();
        playerBlackKing = new HumanPlayer(kingBoard, PiecesColor.BLACK,"Black");
        playerWhiteKing = new HumanPlayer(kingBoard, PiecesColor.WHITE,"White");

        rookBoard = new RookBoard();
        playerBlackRook = new HumanPlayer(rookBoard, PiecesColor.BLACK,"Black");
        playerWhiteRook = new HumanPlayer(rookBoard, PiecesColor.WHITE,"White");

        queenBoard = new QueenBoard();
        playerBlackQueen = new HumanPlayer(queenBoard, PiecesColor.BLACK,"Black");
        playerWhiteQueen = new HumanPlayer(queenBoard, PiecesColor.WHITE,"White");

        emptyBoard = new EmptyBoard();
        playerBlackEmpty = new HumanPlayer(emptyBoard, PiecesColor.BLACK,"Black");
        playerWhiteEmpty = new HumanPlayer(emptyBoard, PiecesColor.WHITE,"White");

    }

    @Test
    public void PiecesStatusTest(){

        PiecesStatus piecesStatusTest = new PiecesStatus(board);
        assert ( piecesStatusTest != null):" PiecesStatus have to contain a board ";

    }

    @Test
    public void isKingInCheckBishopTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (!tmp):" The King isn't in danger";

        Bishop bishop = new Bishop(PiecesColor.WHITE);
        kingBoard.setPiece(1,3,bishop);
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (tmp2):" The King is in danger";


        kingBoard.reset();
        Bishop bishop2 = new Bishop(PiecesColor.BLACK);
        kingBoard.setPiece(6,3,bishop2);
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.WHITE);
        assert (tmp3):" The King is in danger";


    }

    @Test
    public void isKingThreatnedKingTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (!tmp):" The King isn't in danger";

        King king = new King(PiecesColor.WHITE);
        kingBoard.setPiece(1,3,king);
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        King king2 = new King(PiecesColor.BLACK);
        kingBoard.setPiece(6,3,king2);
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.WHITE);
        assert (tmp3):" The King is in danger";


    }

    @Test
    public void isKingThreatnedKnightTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (!tmp):" The King isn't in danger";

        Knight knight = new Knight(PiecesColor.WHITE);
        kingBoard.setPiece(2,3,knight);
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Knight knight2 = new Knight(PiecesColor.BLACK);
        kingBoard.setPiece(5,3,knight2);
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.WHITE);
        assert (tmp3):" The King is in danger";

    }

    @Test
    public void isKingThreatnedPawnTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (!tmp):" The King isn't in danger";

        Pawn pawn = new Pawn(PiecesColor.WHITE);
        kingBoard.setPiece(1,3,pawn);
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Pawn pawn2 = new Pawn(PiecesColor.BLACK);
        kingBoard.setPiece(6,3,pawn2);
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.WHITE);
        assert (tmp3):" The King is in danger";

    }

    @Test
    public void isKingThreatnedQueenTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (!tmp):" The King isn't in danger";

        Queen queen= new Queen(PiecesColor.WHITE);
        kingBoard.setPiece(1,3,queen);
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Queen queen2 = new Queen(PiecesColor.BLACK);
        kingBoard.setPiece(6,3,queen2);
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.WHITE);
        assert (tmp3):" The King is in danger";

    }

    @Test
    public void isKingThreatnedRookTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (!tmp):" The King isn't in danger";

        Rook rook = new Rook(PiecesColor.WHITE);
        kingBoard.setPiece(0,3,rook);
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Rook rook2 = new Rook(PiecesColor.BLACK);
        kingBoard.setPiece(6,4,rook2);
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard, PiecesColor.WHITE);
        assert (tmp3):" The King is in danger";

    }

    @Test
    public void canCastleWhiteRightSideTest(){

        PiecesStatus statusWhiteRook = new PiecesStatus(castlingBoard);
        from = "H1";
        to = "H2";

        Rook rook = (Rook) castlingBoard.getPiece(7, 7);
        King king = (King) castlingBoard.getPiece(7, 4);

        assertFalse("Rook should not have moved", rook.isLeftRookMoved());
        assertFalse("King should not have moved", king.isMoved());
        assertTrue("Castling should be possible", statusWhiteRook.canCastleWhiteRightSide());

        playerWhiteCastling.play(from, to);

        castlingBoard.display();

        assertFalse("The rook moved",  rook.isLeftRookMoved());
        assertFalse("Castling should not be allowed after rook moved", statusWhiteRook.canCastleWhiteRightSide());

    }

    @Test
    public void canCastleWhiteLeftSideTest() {

        PiecesStatus statusWhite = new PiecesStatus(castlingBoard);

        String from = "E1";
        String to = "A1";

        Rook rook = (Rook) castlingBoard.getPiece(7, 0);
        King king = (King) castlingBoard.getPiece(7, 4);

        castlingBoard.display();

        assertFalse("Left white rook should not have moved", rook.isLeftRookMoved());
        assertFalse("White king should not have moved", king.isMoved());
        assertTrue("White left-side castling should be possible", statusWhite.canCastleWhiteLeftSide());

        playerWhiteCastling.play(from, to);

        castlingBoard.display();

    }

    @Test
    public void canCastleBlackRightSideTest() {
        PiecesStatus statusBlack = new PiecesStatus(castlingBoard);

        String from = "H8";
        String to = "H7";

        Rook rook = (Rook) castlingBoard.getPiece(0, 7);
        King king = (King) castlingBoard.getPiece(0, 4);

        assertFalse("Right black rook should not have moved", rook.isLeftRookMoved());
        assertFalse("Black king should not have moved", king.isMoved());
        assertTrue("Black right-side castling should be possible", statusBlack.canCastleBlackRightSide());

        playerBlackCastling.play(from, to);
        assertFalse("Black right-side castling should not be allowed after rook moved", statusBlack.canCastleBlackRightSide());
    }

    @Test
    public void canCastleBlackLeftSideTest() {
        PiecesStatus statusBlack = new PiecesStatus(castlingBoard);

        String from = "A8";
        String to = "A7";

        Rook rook = (Rook) castlingBoard.getPiece(0, 0);
        King king = (King) castlingBoard.getPiece(0, 4);

        assertFalse("Left black rook should not have moved", rook.isLeftRookMoved());
        assertFalse("Black king should not have moved", king.isMoved());
        assertTrue("Black left-side castling should be possible", statusBlack.canCastleBlackLeftSide());

        playerBlackCastling.play(from, to);
        assertFalse("Black left-side castling should not be allowed after rook moved", statusBlack.canCastleBlackLeftSide());
    }


    @Test
    public void testCannotCastleWhenOnlyRookMoved() {
        PiecesStatus status = new PiecesStatus(castlingBoard);

        Rook rook = (Rook) castlingBoard.getPiece(0, 0);
        King king = (King) castlingBoard.getPiece(0, 4);

        rook.setLeftRookMoved(true);
        king.setHasMoved(false);

        assertFalse(status.canCastleBlackLeftSide());
    }

    @Test
    public void testCannotCastleWhenOnlyKingMoved() {
        PiecesStatus status = new PiecesStatus(castlingBoard);

        Rook rook = (Rook) castlingBoard.getPiece(0, 0);
        King king = (King) castlingBoard.getPiece(0, 4);

        rook.setLeftRookMoved(false);
        king.setHasMoved(true);

        assertFalse(status.canCastleBlackLeftSide());
    }

    @Test
    public void stalemateTest(){

        PiecesStatus status = new PiecesStatus(emptyBoard);

        emptyBoard.setPiece(2,6,new Queen(PiecesColor.BLACK));
        emptyBoard.setPiece(0,7,new King(PiecesColor.WHITE));
        assertTrue(status.stalemate(emptyBoard, PiecesColor.WHITE));
        emptyBoard.reset();

        emptyBoard.setPiece(0,5,new King(PiecesColor.BLACK));
        emptyBoard.setPiece(1,5,new Pawn(PiecesColor.WHITE));
        emptyBoard.setPiece(2,5,new King(PiecesColor.WHITE));
        assertTrue(status.stalemate(emptyBoard, PiecesColor.BLACK));
        emptyBoard.reset();

        emptyBoard.setPiece(0,0, new King(PiecesColor.BLACK));
        emptyBoard.setPiece(1,2, new Rook(PiecesColor.WHITE));
        emptyBoard.setPiece(2,1, new Rook(PiecesColor.WHITE));

        emptyBoard.setPiece(6,6, new Pawn(PiecesColor.BLACK));
        emptyBoard.setPiece(7,6, new Pawn(PiecesColor.WHITE));

        emptyBoard.display();
        assertTrue(status.stalemate(emptyBoard, PiecesColor.BLACK));
        emptyBoard.reset();

    }
}