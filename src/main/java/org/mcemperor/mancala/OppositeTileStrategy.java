package org.mcemperor.mancala;

import java.util.List;

/**
 * Strategy to select the 'opposite tile'.
 */
public interface OppositeTileStrategy {

    List<Tile> getOppositeTiles(Board board, int boardIndex, int tileIndex);

}
