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

    /**
     * Setter
     * @param rounds the desire round
     */
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    /**
     * Getter
     * @return the current round
     */
    public int getRounds() {
        return this.rounds;
    }

    /**
     * Based on the current round, return the player currently playing
     * @param rounds
     * @return WHITE if round % 2 == 0 else BLACK
     */
    public String getPlayerNameBasedOnRound(int rounds) {

        if (rounds % 2 == 0) return "WHITE";
        return "BLACK";

    }

    /**
     * Generate a random to find you start.
     * Then, according to the current round, ask the move of the current player, play it and display the board
     */
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
                    if ( piecesStatus.stalemate(board,Color.WHITE)){
                        System.out.println("Black Wins");
                    }
                    white.play(from, to);
                    if (piecesStatus.isKingInCheck(board, Color.WHITE)) {
                        System.out.println("White King in check");
                    }
                } else {
                    if ( piecesStatus.stalemate(board,Color.BLACK)){
                        System.out.println("White Wins");
                    }
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

        } while (!gameStatus.isCheckmate(Color.WHITE) && !gameStatus.isCheckmate(Color.BLACK));
    }
}