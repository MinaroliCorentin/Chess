package src.chess.test;

import org.junit.Before;
import org.junit.Test;
import src.chess.factory.Board;
import src.chess.factory.EmptyBoard;
import src.chess.factory.StandartBoard;
import src.chess.model.pieces.*;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board ;

    @Before
    public void setUp() {

        this.board = new EmptyBoard();

    }

    @Test
    public void resetTest() {

        board.setPiece(1,1,new King(Color.WHITE));
        assertNotNull("The board contain a piece ",board.getPiece(1, 1));
        board.reset();
        assertNull("The board don't contain a piece",board.getPiece(1, 1));

    }

    @Test
    public void setBoardTest(){
    assertEquals(0, board.getPiecesMap().size());
    Board boardTest = new StandartBoard();
    board.setBoard(boardTest.getBoard());
    assertEquals(32, board.getPiecesMap().size());
    }

    @Test
    public void getBoardTest(){

        Knight knight = new Knight(Color.WHITE);
        Pieces[][] boardArray = board.getBoard();
        assertNull(boardArray[0][0]);

        this.board.setPiece(0,0,knight);
        boardArray = board.getBoard();
        assertNotNull(boardArray[0][0]);


    }

    @Test
    public void GetSetPieceTest() {
        Knight knight = new Knight(Color.WHITE);
        board.setPiece(4, 4, knight);
        assertEquals(knight, board.getPiece(4, 4));

        board.setPiece(4, 4, null);
        assertNull(board.getPiece(4, 4));
    }

    @Test
    public void isEmptyTest(){

        board.setPiece(1,1,new King(Color.WHITE));
        assertFalse("Have to be true",board.isEmpty(1,1));
        assertTrue("Have to be true",board.isEmpty(1,0));

    }

    @Test
    public void IsBoundTest() {
        assertTrue(board.isBound(0, 0));
        assertTrue(board.isBound(7, 7));
        assertFalse(board.isBound(-1, 0));
        assertFalse(board.isBound(0, 8));
    }

    @Test
    public void HasOpponentPieceOrNullTest() {
        board.setPiece(6, 1, new Knight(Color.BLACK));
        assertTrue(board.hasOpponentPieceOrNull(6, 1, Color.WHITE));
        assertFalse(board.hasOpponentPieceOrNull(7, 1, Color.WHITE));
        assertFalse(board.hasOpponentPieceOrNull(0, 0, Color.WHITE));
    }

    @Test
    public void getPiecesMapTest(){

        Rook rook = new Rook(Color.WHITE);
        Queen queen = new Queen(Color.WHITE);
        Knight knight = new Knight(Color.BLACK);
        King king = new King(Color.WHITE);
        Pawn pawn = new Pawn(Color.WHITE);

        assertEquals(0, board.getPiecesMap().size());


        board.setPiece(0,0,rook);
        assertTrue(board.getPiecesMap().containsValue(rook));
        assertEquals(1, board.getPiecesMap().size());

        board.setPiece(1,1,queen);
        assertTrue(board.getPiecesMap().containsValue(queen));
        assertEquals(2, board.getPiecesMap().size());

        board.setPiece(2,2,knight);
        assertTrue(board.getPiecesMap().containsValue(knight));
        assertEquals(3, board.getPiecesMap().size());

        board.setPiece(3,3,king);
        assertTrue(board.getPiecesMap().containsValue(king));
        assertEquals(4, board.getPiecesMap().size());

        board.setPiece(4,4,pawn);
        assertTrue(board.getPiecesMap().containsValue(pawn));
        assertEquals(5, board.getPiecesMap().size());

    }

    @Test
    public void updatePiecesMapTest(){

        Board standardBoard = new StandartBoard();
        this.board.setBoard(standardBoard.getBoard());
        assertEquals(32, board.getPiecesMap().size());
        board.display();

        Player white = new HumanPlayer(board,Color.WHITE);
        Player black = new HumanPlayer(board,Color.BLACK);

        white.play("B2","B4");
        black.play("A7","A5");
        white.play("B4","A5");

        assertEquals(31, board.getPiecesMap().size());
        board.display();

    }
}