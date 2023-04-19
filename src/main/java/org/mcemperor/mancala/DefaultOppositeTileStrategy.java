package org.mcemperor.mancala;

import java.util.List;

public class DefaultOppositeTileStrategy implements OppositeTileStrategy {

    @Override
    public List<Tile> getOppositeTiles(Board board, int boardSideIndex, int tileIndex) {
        int newSideIndex = (boardSideIndex + 1) % board.getNumberOfSides();
        int newTileIndex = board.getTilesPerPlayer() - 1 - tileIndex;
        return List.of(
            board.getSide(newSideIndex).getTile(newTileIndex)
        );
    }
}
