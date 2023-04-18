package org.mcemperor.mancala;

import java.util.List;

public interface OppositeTileStrategy {

    List<Tile> getOppositeTiles(Board board, int boardIndex, int tileIndex);

}
