package org.mcemperor.mancala;

import lombok.ToString;

import java.util.List;
import java.util.stream.Stream;

@ToString
public class BoardSide {

    private final List<Tile> tiles;

    private final Tile mancala = new Tile(0);

    public BoardSide(int numberOfNormalTiles, int gems) {
        this.tiles = Stream.generate(() -> new Tile(gems))
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
        for (int i = 0; i < amountToDistribute; i++) {
            this.tiles.get(i + startIndex).addGems(1);
        }

        // Check whether there are gems to add to the Mancala tile
        boolean lastTileWasMancala = false;
        if (includeMancalaTile && amount > amountToDistribute) {
            this.mancala.addGems(1);
            amountToDistribute++;
            lastTileWasMancala = true;
        }
        return new DistributionResult(amountToDistribute, lastTileWasMancala);
    }

    public int peekGems(int index) {
        return tiles.get(index).getGems();
    }

    public int peekGemsAtMancala() {
        return mancala.getGems();
    }

    public int removeGems(int index) {
        return tiles.get(index).removeAllGems();
    }
}
