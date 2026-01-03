package src.chess.gamemanagement;

import src.chess.factory.Board;
import src.chess.gamestatus.GameStatus;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.Player;
import src.chess.model.pieces.PiecesStatus;
import java.util.Scanner;

public class GameManagementTerminal extends GameManagement {

    public GameManagementTerminal(Board board, Player white, Player black, GameStatus gameStatus) {
        super(board, white, black, gameStatus);
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        getBoard().display();

        while (!isGameOver(getPlayerBaseOnRoundReversed().getColor()) && !isDraw() ) {

            System.out.println(getGameStatus().getDrawCounter());

            System.out.println( getPlayerNameBasedOnRound() + " Turn ");
            System.out.print("From : ");
            String from = input.nextLine();
            System.out.print("To : ");
            String to = input.nextLine();

            this.playMove(from, to);
        }

        if (getGameStatus().isDraw()) {
            System.out.println("It's a Draw ! ");
        } else {
            System.out.println( getPlayerBaseOnRoundReversed().getPlayerName() + "Wins !");
        }
    }


    public void playMove(String from, String to) {
        PiecesStatus piecesStatus = new PiecesStatus(getBoard());

        try {
            if ((this.getRounds()) % 2 == 0) {
                if (piecesStatus.stalemate(getBoard(), PiecesColor.WHITE)) return;
                getWhite().play(from, to);
            } else {
                if (piecesStatus.stalemate(getBoard(), PiecesColor.BLACK)) return;
                getBlack().play(from, to);
            }

            getGameStatus().promoting();
            getBoard().display();

            setRounds(getRounds() + 1);
            getGameStatus().inscreaseDrawCounter(); //

        } catch (RuntimeException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }
}