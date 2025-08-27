package src.chess.GameManagement;

import src.chess.factory.Board;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.Player;
import src.chess.gamestatus.GameStatusTerminal;
import src.chess.status.PiecesStatus;

import java.util.Scanner;

public class GameManagementTerminal extends GameManagement {

    public GameManagementTerminal(Board board, Player white, Player black) {
        super(board,white,black);
    }

    /**
     * Generate a random to find who start.
     * Then, according to the current round, ask the move of the current player, play it and display the board
     */
    public void chess() {

        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(getBoard());
        PiecesStatus piecesStatus = new PiecesStatus(getBoard());
        Scanner input = new Scanner(System.in);
        getBoard().display();

        do {

            System.out.println("What is your move : " + getPlayerNameBasedOnRound());
            System.out.print("From : ");
            String from = input.nextLine();
            System.out.print("To : ");
            String to = input.nextLine();

            try {
                if ((this.getRounds() ) % 2 == 0) {
                    if ( piecesStatus.stalemate(getBoard(), PiecesColor.WHITE)){
                        System.out.println("Stalemate Black Wins");
                    }
                    getWhite().play(from, to);
                    if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.WHITE)) {
                        System.out.println("White King in check");
                    }
                } else {
                    if ( piecesStatus.stalemate(getBoard(), PiecesColor.BLACK)){
                        System.out.println("Stalemate White Wins");
                    }
                    getBlack().play(from, to);
                    if (piecesStatus.isKingInCheck(getBoard(), PiecesColor.BLACK)) {
                        System.out.println("Black King in check");
                    }
                }

                gameStatusTerminal.promoting();
                getBoard().display();
                setRounds(getRounds() + 1);
                gameStatusTerminal.setDrawCounter(getRounds() + 1 );

            } catch (RuntimeException e) {
                System.err.println("Erreur : " + e.getMessage());
            }

        } while (!gameStatusTerminal.isCheckmate(PiecesColor.WHITE) && !gameStatusTerminal.isCheckmate(PiecesColor.BLACK) && !gameStatusTerminal.isDraw());
    }
}