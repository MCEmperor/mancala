package org.mcemperor.mancala;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        var game = new Game(new DefaultBoard(2, 6, 4));
        System.out.println(drawBoard(game.getBoard()));

        while (!game.isOver()) {
            int player = game.getPlayerTurn();
            int[] possibleMoves = game.determineMoves(player);
            System.out.println("Player " + (player + 1) + "'s turn");
            System.out.print("Please input a tile (possibilities: " + Arrays.toString(possibleMoves) + "): ");
            int tile = scanner.nextInt();
            if (game.checkMove(player, tile)) {
                game.pick(player, tile);
                System.out.println(drawBoard(game.getBoard()));
            }
            else {
                System.out.println("Invalid tile, please input a valid one");
            }
        }

        // End game
        var stats = game.summaryStatistics();
        int winner = stats.getWinningPlayer();
        System.out.println("Player " + (winner + 1) + " wins with " + stats.getPointsOf(winner));
    }

    private static String drawBoard(Board board) {
        var sb = new StringBuilder();
        for (int line = 1; line <= 5; line++) {
            // Draw player 2's mancala tile segment
            sb.append(switch (line) {
                case 1, 5 -> "+-----+";
                case 2, 4 -> "|     |";
                case 3 -> "| " + "%3s".formatted(board.getSide(1).peekGemsAtMancala()) + " |";
                default -> "";
            });

            // Normal tiles
            for (int i = 0; i < board.getTilesPerPlayer(); i++) {
                sb.append(switch (line) {
                    case 1, 3, 5  -> "-----+";
                    case 2 -> " " + "%3s".formatted(board.getSide(1).peekGems(board.getTilesPerPlayer() - 1 - i)) + " |";
                    case 4 -> " " + "%3s".formatted(board.getSide(0).peekGems(i)) + " |";
                    default -> "";
                });
            }

            sb.append(switch (line) {
                case 1, 5 -> "-----+";
                case 2, 4 -> "     |";
                case 3 -> " " + "%3s".formatted(board.getSide(0).peekGemsAtMancala()) + " |";
                default -> "";
            });
            sb.append("\n");
        }
        return sb.toString();
    }
}
