package org.mcemperor.mancala;

import java.util.List;
import java.util.stream.Stream;

public class DefaultBoard implements Board {

    private final int tilesPerPlayer;

    private final List<BoardSide> sides;

    /**
     * Constructs a board with the given number of players, tiles per player, gems to start with on each tile, and a
     * strategy to determine which tile is the opposite tile of another one.
     *
     * @param numberOfSides The number of players to.
     * @param tilesPerSide The number of tiles per side.
     * @param startGems The number of start gems for each tile.
     */
    public DefaultBoard(int numberOfSides, int tilesPerSide, int startGems) {
        this.tilesPerPlayer = tilesPerSide;
        this.sides = Stream.generate(() -> new BoardSide(tilesPerSide, startGems))
            .limit(numberOfSides)
            .toList();
    }

    /**
     * Distributes the given number of gems over the board.
     *
     * @param boardSideIndex The board index to start with.
     * @param tileIndex The tile index to start with.
     * @param totalGems The total number of gems to distribute.
     * @return A DistributionResult instance with the resulting statistics.
     */
    public DistributionResult distributeGems(final int boardSideIndex, final int tileIndex, final int totalGems) {
        int gemsLeft = totalGems;
        int currBoardSide = boardSideIndex;
        int currTile = tileIndex;
        DistributionResult result = null;
        while (gemsLeft > 0) {
            var anotherResult = sides.get(currBoardSide).distributeGems(gemsLeft, currTile, currBoardSide == boardSideIndex);
            result = DistributionResult.merge(result, anotherResult)
                .withLastTileBoardSideIndex(currBoardSide);
            gemsLeft -= anotherResult.gemsDistributed();
            currBoardSide = (currBoardSide + 1) % sides.size();
            currTile = 0;
        }
        return result;
    }

    @Override
    public BoardSide getSide(int index) {
        return sides.get(index);
    }

    @Override
    public int getTilesPerPlayer() {
        return tilesPerPlayer;
    }

    @Override
    public int getNumberOfSides() {
        return sides.size();
    }
}
