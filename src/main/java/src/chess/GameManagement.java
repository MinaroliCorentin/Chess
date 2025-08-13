package src.chess;

import src.chess.factory.Board;
import src.chess.model.pieces.Color;
import src.chess.model.players.Player;
import src.chess.status.GameStatus;
import src.chess.status.PiecesStatus;

import java.util.Random;
import java.util.Scanner;

public class GameManagement {

    private Board board;
    private Player white;
    private Player black;
    private int rounds;

    public GameManagement(Board board, Player white, Player black) {
        this.board = board;
        this.white = white;
        this.black = black;
        this.rounds = 0;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getRounds() {
        return this.rounds;
    }

    public String getPlayerNameBasedOnRound(int rounds) {

        if (rounds % 2 == 0) return "WHITE";
        return "BLACK";

    }

    public void chess() {

        GameStatus gameStatus = new GameStatus(board);
        PiecesStatus piecesStatus = new PiecesStatus(board);
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int randint = random.nextInt(0, 2);
        board.display();

        do {
            System.out.println("What is your move : " + getPlayerNameBasedOnRound(this.rounds + randint));
            System.out.print("From : ");
            String from = input.nextLine();
            System.out.print("To : ");
            String to = input.nextLine();

            try {
                if ((this.rounds + randint) % 2 == 0) {
                    white.play(from, to);
                    if (piecesStatus.isKingInCheck(board, Color.WHITE)) {
                        System.out.println("White King in check");
                    }
                } else {
                    black.play(from, to);
                    if (piecesStatus.isKingInCheck(board, Color.BLACK)) {
                        System.out.println("Black King in check");
                    }
                }

                gameStatus.promoting();
                board.display();
                setRounds(getRounds() + 1);

            } catch (RuntimeException e) {
                System.err.println("Erreur : " + e.getMessage());
            }

        } while (!gameStatus.blackCheckmate() && !gameStatus.whiteCheckmate());
    }
}