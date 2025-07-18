package src.chess.test;

import org.junit.Before;
import org.junit.Test;
import src.chess.factory.*;
import src.chess.model.pieces.*;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;
import src.chess.status.PiecesStatus;

public class PiecesStatusTest {

    private Board bishopBoard;
    private Board kingBoard;
    private Board rookBoard;
    private Board board;
    private Board queenBoard;

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
    String from ;
    String to;

    @Before
    public void setUp() {

        board = new StandartBoard();
        playerBlack = new HumanPlayer(board, Color.BLACK);
        playerWhite = new HumanPlayer(board, Color.WHITE);

        bishopBoard = new BishopBoard();
        playerBlackBishop = new HumanPlayer(bishopBoard, Color.BLACK);
        playerWhiteBishop = new HumanPlayer(bishopBoard, Color.WHITE);

        kingBoard = new KingBoard();
        playerBlackKing = new HumanPlayer(kingBoard, Color.BLACK);
        playerWhiteKing = new HumanPlayer(kingBoard, Color.WHITE);

        rookBoard = new RookBoard();
        playerBlackRook = new HumanPlayer(rookBoard, Color.BLACK);
        playerWhiteRook = new HumanPlayer(rookBoard, Color.WHITE);

        queenBoard = new QueenBoard();
        playerBlackQueen = new HumanPlayer(queenBoard, Color.BLACK);
        playerWhiteQueen = new HumanPlayer(queenBoard, Color.WHITE);

    }

    @Test
    public void PiecesStatusTest(){

        PiecesStatus piecesStatusTest = new PiecesStatus(board);
        assert ( piecesStatusTest != null):" PiecesStatus have to contain a board ";

    }

    @Test
    public void isKingInCheckBishopTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (!tmp):" The King isn't in danger";

        kingBoard.displayWithIndices();
        Bishop bishop = new Bishop(Color.WHITE);
        kingBoard.setPiece(1,3,bishop);
        kingBoard.displayWithIndices();
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (tmp2):" The King is in danger";


        kingBoard.reset();
        Bishop bishop2 = new Bishop(Color.BLACK);
        kingBoard.setPiece(6,3,bishop2);
        kingBoard.displayWithIndices();
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard,Color.WHITE);
        assert (tmp3):" The King is in danger";


    }

    @Test
    public void isKingThreatnedKingTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (!tmp):" The King isn't in danger";

        kingBoard.displayWithIndices();
        King king = new King(Color.WHITE);
        kingBoard.setPiece(1,3,king);
        kingBoard.displayWithIndices();
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        King king2 = new King(Color.BLACK);
        kingBoard.setPiece(6,3,king2);
        kingBoard.displayWithIndices();
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard,Color.WHITE);
        assert (tmp3):" The King is in danger";


    }

    @Test
    public void isKingThreatnedKnightTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (!tmp):" The King isn't in danger";

        kingBoard.displayWithIndices();
        Knight knight = new Knight(Color.WHITE);
        kingBoard.setPiece(2,3,knight);
        kingBoard.displayWithIndices();
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Knight knight2 = new Knight(Color.BLACK);
        kingBoard.setPiece(5,3,knight2);
        kingBoard.displayWithIndices();
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard,Color.WHITE);
        assert (tmp3):" The King is in danger";

    }

    @Test
    public void isKingThreatnedPawnTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (!tmp):" The King isn't in danger";

        kingBoard.displayWithIndices();
        Pawn pawn = new Pawn(Color.WHITE);
        kingBoard.setPiece(1,3,pawn);
        kingBoard.displayWithIndices();
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Pawn pawn2 = new Pawn(Color.BLACK);
        kingBoard.setPiece(6,3,pawn2);
        kingBoard.displayWithIndices();
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard,Color.WHITE);
        assert (tmp3):" The King is in danger";

    }

    @Test
    public void isKingThreatnedQueenTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (!tmp):" The King isn't in danger";

        kingBoard.displayWithIndices();
        Queen queen= new Queen(Color.WHITE);
        kingBoard.setPiece(1,3,queen);
        kingBoard.displayWithIndices();
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Queen queen2 = new Queen(Color.BLACK);
        kingBoard.setPiece(6,3,queen2);
        kingBoard.displayWithIndices();
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard,Color.WHITE);
        assert (tmp3):" The King is in danger";

    }

    @Test
    public void isKingThreatnedRookTest(){

        PiecesStatus statusKingBoard = new PiecesStatus(kingBoard);
        boolean tmp = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (!tmp):" The King isn't in danger";

        kingBoard.displayWithIndices();
        Rook rook = new Rook(Color.WHITE);
        kingBoard.setPiece(0,3,rook);
        kingBoard.displayWithIndices();
        boolean tmp2 = statusKingBoard.isKingInCheck(kingBoard,Color.BLACK);
        assert (tmp2):" The King is in danger";

        kingBoard.reset();
        Rook rook2 = new Rook(Color.BLACK);
        kingBoard.setPiece(6,4,rook2);
        kingBoard.displayWithIndices();
        boolean tmp3 = statusKingBoard.isKingInCheck(kingBoard,Color.WHITE);
        assert (tmp3):" The King is in danger";

    }

}
