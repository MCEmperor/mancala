package org.mcemperor.mancala;

import lombok.ToString;

import java.util.List;
import java.util.stream.Stream;

@ToString
public class BoardSide {

    private final List<Tile> tiles;

    private final Tile mancala = new Tile(0);

    public BoardSide(int numberOfNormalTiles, int gems) {
        tiles = Stream.generate(() -> new Tile(gems))
            .limit(numberOfNormalTiles)
            .toList();
    }

    /**
     * Distributes the given amount of gems over the tiles of this board side, starting at position {@code startIndex},
     * up to and including the last normal tile, or including the mancala tile if {@code includeMancalaTile} is set to
     * {@code true}.
     *
     * @param amount The amount of gems to be distributed.
     * @param startIndex The start index from where to distribute.
     * @param includeMancalaTile Whether to include the mancala tile.
     * @return The total number of gems distributed.
     */
    public DistributionResult distributeGems(int amount, int startIndex, boolean includeMancalaTile) {
        int amountToDistribute = Math.min(amount, tiles.size() - startIndex);
        int lastTileIndex = tiles.size();
        int gemsDistributedAtLastTile = 0;
        for (int i = 0; i < amountToDistribute; i++) {
            lastTileIndex = i + startIndex;
            tiles.get(lastTileIndex).addGems(1);
            gemsDistributedAtLastTile = 1;
        }

        // Check whether there are gems to add to the Mancala tile
        if (includeMancalaTile && amount > amountToDistribute) {
            mancala.addGems(1);
            amountToDistribute++;
            lastTileIndex = -1;
            gemsDistributedAtLastTile = 1;
        }
        return new DistributionResult(amountToDistribute, -1, lastTileIndex, gemsDistributedAtLastTile);
    }

    public Tile getMancala() {
        return mancala;
    }

    public Tile getTile(int index) {
        if (!(-1 <= index && index < tiles.size())) {
            throw new IllegalArgumentException("Index should be >= -1 and < number of normal tiles");
        }
        return (index == -1 ? mancala : tiles.get(index));
    }

    public int peekGems(int index) {
        return getTile(index).getGems();
    }

    public int peekGemsAtMancala() {
        return mancala.getGems();
    }

    public int removeAllGems(int index) {
        return getTile(index)
            .removeAllGems();
    }
}
