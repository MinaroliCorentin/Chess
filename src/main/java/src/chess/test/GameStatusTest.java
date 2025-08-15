package src.chess.test;

import org.junit.Test;
import src.chess.factory.EmptyBoard;
import src.chess.factory.PawnBoard;
import src.chess.model.handler.PromoteHandler;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.King;
import src.chess.model.pieces.Pawn;
import src.chess.model.pieces.Queen;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;
import src.chess.status.GameStatus;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GameStatusTest {

    @Test
    public void promotionAsBlackRookTest(){

        String testInput = "1";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, Color.BLACK);

            BlackPawnPlayer.play("H7","H5");
            BlackPawnPlayer.play("H5","H4");
            BlackPawnPlayer.play("H4","H3");
            BlackPawnPlayer.play("H3","H2");
            BlackPawnPlayer.play("H2","H1");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(7,7).isRook()):" The Piece at H1 (7,7) must be a rook";

        } finally {
            System.setIn(originalIn);
        }

    }

    @Test
    public void promotionAsBlackKnigthTest(){

        String testInput = "2";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, Color.BLACK);

            BlackPawnPlayer.play("H7","H5");
            BlackPawnPlayer.play("H5","H4");
            BlackPawnPlayer.play("H4","H3");
            BlackPawnPlayer.play("H3","H2");
            BlackPawnPlayer.play("H2","H1");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(7,7).isKnight()):" The Piece at H1 (7,7) must be a Knight";

        } finally {
            System.setIn(originalIn);
        }

    }

    @Test
    public void promotionAsBlackBishopTest(){

        String testInput = "3";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, Color.BLACK);

            BlackPawnPlayer.play("H7","H5");
            BlackPawnPlayer.play("H5","H4");
            BlackPawnPlayer.play("H4","H3");
            BlackPawnPlayer.play("H3","H2");
            BlackPawnPlayer.play("H2","H1");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(7,7).isBishop()):" The Piece at H1 (7,7) must be a Bishop";

        } finally {
            System.setIn(originalIn);
        }

    }

    @Test
    public void promotionAsBlackQueenTest(){

        String testInput = "4";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, Color.BLACK);

            BlackPawnPlayer.play("H7","H5");
            BlackPawnPlayer.play("H5","H4");
            BlackPawnPlayer.play("H4","H3");
            BlackPawnPlayer.play("H3","H2");
            BlackPawnPlayer.play("H2","H1");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(7,7).isQueen()):" The Piece at H1 (7,7) must be a queen";

        } finally {
            System.setIn(originalIn);
        }

    }

    @Test
    public void promotionAsWhiteRookTest(){

        String testInput = "1";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, Color.WHITE);

            WhitePawnPlayer.play("A2","A4");
            WhitePawnPlayer.play("A4","A5");
            WhitePawnPlayer.play("A5","A6");
            WhitePawnPlayer.play("A6","A7");
            WhitePawnPlayer.play("A7","A8");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(0,0).isRook()):" The Piece at A8 (0,0) must be a rook";

        } finally {
            System.setIn(originalIn);
        }

    }

    @Test
    public void promotionAsWhiteKnigthTest(){

        String testInput = "2";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, Color.WHITE);

            WhitePawnPlayer.play("A2","A4");
            WhitePawnPlayer.play("A4","A5");
            WhitePawnPlayer.play("A5","A6");
            WhitePawnPlayer.play("A6","A7");
            WhitePawnPlayer.play("A7","A8");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(0,0).isKnight()):" The Piece at A8 (0,0) must be a knight";

        } finally {
            System.out.println("test");
            System.setIn(originalIn);
        }

    }
    @Test
    public void promotionAsWhiteBishopTest(){

        String testInput = "3";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, Color.WHITE);

            WhitePawnPlayer.play("A2","A4");
            WhitePawnPlayer.play("A4","A5");
            WhitePawnPlayer.play("A5","A6");
            WhitePawnPlayer.play("A6","A7");
            WhitePawnPlayer.play("A7","A8");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(0,0).isBishop()):" The Piece at A8 (0,0) must be a Bishop";

        } finally {
            System.setIn(originalIn);
        }

    }
    @Test
    public void promotionAsWhiteQueenTest(){

        String testInput = "4";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, Color.WHITE);

            WhitePawnPlayer.play("A2","A4");
            WhitePawnPlayer.play("A4","A5");
            WhitePawnPlayer.play("A5","A6");
            WhitePawnPlayer.play("A6","A7");
            WhitePawnPlayer.play("A7","A8");

            board.display();
            GameStatus gameStatus = new GameStatus(board);
            gameStatus.promoting();
            assert(board.getPiece(0,0).isQueen()):" The Piece at A8 (0,0) must be a Queen";

        } finally {
            System.setIn(originalIn);
        }

    }

    @Test
    public void checkMateWhiteTest(){

        EmptyBoard board = new EmptyBoard();
        board.setPiece(0,2,new King(Color.WHITE));
        board.setPiece(0,1,new Pawn(Color.BLACK));
        board.setPiece(0,3,new Pawn(Color.BLACK));
        board.setPiece(1,3,new Pawn(Color.BLACK));
        board.setPiece(1,1,new Pawn(Color.BLACK));
        board.setPiece(1,2,new Pawn(Color.BLACK));

        board.displayWithIndices();
        GameStatus gameStatus = new GameStatus(board);
        boolean checkmate1 = gameStatus.isCheckmate(Color.WHITE);
        assert( !checkmate1 ):" The king is surrounded by pawn but can move ";
        board.reset();

        board.setPiece(0,2,new King(Color.WHITE));
        board.setPiece(0,1,new Queen(Color.BLACK));
        board.setPiece(0,3,new Queen(Color.BLACK));
        board.setPiece(1,3,new Queen(Color.BLACK));
        board.setPiece(1,1,new Queen(Color.BLACK));
        board.setPiece(1,2,new Queen(Color.BLACK));
        board.displayWithIndices();
        boolean checkmate2 = gameStatus.isCheckmate(Color.WHITE);
        assert( checkmate2 ):" The king is surrounded by Queen and cannot move";

    }

    @Test
    public void checkMateBlackTest(){

        EmptyBoard board = new EmptyBoard();
        board.setPiece(0,2,new King(Color.BLACK));
        board.setPiece(0,1,new Pawn(Color.WHITE));
        board.setPiece(0,3,new Pawn(Color.WHITE));
        board.setPiece(1,3,new Pawn(Color.WHITE));
        board.setPiece(1,1,new Pawn(Color.WHITE));
        board.setPiece(1,2,new Pawn(Color.WHITE));

        board.displayWithIndices();
        GameStatus gameStatus = new GameStatus(board);
        boolean checkmate1 = gameStatus.isCheckmate(Color.BLACK);
        assert( !checkmate1 ):" The king is surrounded by pawn but can move ";
        board.reset();

        board.setPiece(0,2,new King(Color.BLACK));
        board.setPiece(0,1,new Queen(Color.WHITE));
        board.setPiece(0,3,new Queen(Color.WHITE));
        board.setPiece(1,3,new Queen(Color.WHITE));
        board.setPiece(1,1,new Queen(Color.WHITE));
        board.setPiece(1,2,new Queen(Color.WHITE));
        board.displayWithIndices();
        boolean checkmate2 = gameStatus.isCheckmate(Color.BLACK);
        assert( checkmate2 ):" The king is surrounded by Queen and cannot move";

    }

}
