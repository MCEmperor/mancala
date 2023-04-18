package org.mcemperor.mancala;

public record DistributionResult(int gemsDistributed, int lastTileBoardSideIndex, int lastTileIndex, int gemsDistributedAtLastTile) {

    public DistributionResult withLastTileBoardSideIndex(int lastTileBoardSideIndex) {
        return new DistributionResult(
            gemsDistributed(),
            lastTileBoardSideIndex,
            lastTileIndex(),
            gemsDistributedAtLastTile()
        );
    }

    public static DistributionResult merge(DistributionResult left, DistributionResult right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return new DistributionResult(
            left.gemsDistributed() + right.gemsDistributed(),
            right.lastTileBoardSideIndex(),
            right.lastTileIndex(),
            right.gemsDistributedAtLastTile()
        );
    }
}
