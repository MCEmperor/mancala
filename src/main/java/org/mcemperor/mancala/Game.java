package org.mcemperor.mancala;

import java.util.List;
import java.util.stream.IntStream;

public class Game {

    private volatile boolean isOver = false;

    private int playerTurn = 0;

    private final DefaultBoard board;

    private final OppositeTileStrategy oppositeTileStrategy;

    public Game(DefaultBoard board) {
        this.board = board;
        this.oppositeTileStrategy = new DefaultOppositeTileStrategy();
    }

    /**
     * Picks the gems of the given tile and distributes each one over the remaining tiles, counterclockwise.
     *
     * @param player The player performing the action.
     * @param tileIndex The index of the tile to empty.
     * @return The distribution results.
     */
    public void pick(int player, int tileIndex) {
        if (player != playerTurn) {
            throw new IllegalArgumentException("player != playerTurn");
        }
        if (isOver) {
            throw new IllegalStateException("game is over");
        }

        int boardSideIndex = playerTurn;
        int gems = board.getSide(boardSideIndex).removeAllGems(tileIndex);
        DistributionResult result = board.distributeGems(boardSideIndex, tileIndex + 1, gems);

        // If the last tile was empty and belongs to the player on turn, then the player may steal all the gems of the
        // opposite tile on the other board side.
        if (result != null) {
            if (result.lastTileIndex() != -1 && result.lastTileBoardSideIndex() == boardSideIndex && result.gemsDistributedAtLastTile() == board.getSide(boardSideIndex).peekGems(result.lastTileIndex()) && oppositeGemsAvailable(boardSideIndex, result.lastTileIndex())) {
                stealFromOppositePlayers(result.lastTileBoardSideIndex(), result.lastTileIndex());
                // Also, you may capture the last distributed gem on your own side
                var boardSide = board.getSide(player);
                int gemsPicked = boardSide.removeAllGems(result.lastTileIndex());
                boardSide.getMancala().addGems(gemsPicked);
            }

            // Ends the turn, except when last tile is Mancala tile
            if (result.lastTileIndex() != -1) {
                endTurn();
            }
        }

        if (determineGameOver()) {
            // Process remaining gems
            int playerWithRemainingGems = (playerTurn + 1) % board.getNumberOfSides();
            for (int i = 0; i < board.getTilesPerPlayer(); i++) {
                var boardSide = board.getSide(playerWithRemainingGems);
                int remainingGems = boardSide.getTile(i).removeAllGems();
                boardSide.getMancala().addGems(remainingGems);
            }

            isOver = true;
        }
    }

    private boolean oppositeGemsAvailable(int boardSideIndex, int tileIndex) {
        int gemsAvailable = oppositeTileStrategy.getOppositeTiles(board, boardSideIndex, tileIndex).stream()
            .mapToInt(Tile::getGems)
            .sum();
        return (gemsAvailable > 0);
    }

    public GameStatistics summaryStatistics() {
        if (!isOver) {
            throw new IllegalStateException("Game is not over - yet");
        }

        List<Integer> pointsPerPlayer = IntStream.range(0, board.getNumberOfSides())
            .map(i -> board.getSide(i).getMancala().getGems())
            .boxed()
            .toList();
        return new GameStatistics(pointsPerPlayer);
    }

    /**
     * Steals the gems of the opposite sides based on the given player and tile index.
     *
     * @param player The current player.
     * @param tileIndex The index of the current tile.
     */
    private void stealFromOppositePlayers(int player, int tileIndex) {
        int stolenGems = 0;
        for (Tile tile : oppositeTileStrategy.getOppositeTiles(board, player, tileIndex)) {
            stolenGems = tile.removeAllGems();
        }
        board.getSide(player).getMancala()
            .addGems(stolenGems);
    }

    /**
     * Ends the turn for the current player, passing it to the next player.
     */
    private void endTurn() {
        playerTurn = (playerTurn + 1) % board.getNumberOfSides();
    }

    private boolean determineGameOver() {
        return determineMoves(playerTurn).length == 0;
    }

    public boolean checkMove(int player, int index) {
        return board.getSide(player).peekGems(index) > 0;
    }

    public int[] determineMoves(int player) {
        return IntStream.range(0, board.getTilesPerPlayer())
            .filter(i -> checkMove(player, i))
            .toArray();
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public boolean isOver() {
        return isOver;
    }
}
