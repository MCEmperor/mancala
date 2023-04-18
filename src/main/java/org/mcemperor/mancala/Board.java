package org.mcemperor.mancala;

import java.util.List;

public interface Board {

    BoardSide getSide(int index);

    int getTilesPerPlayer();

    int getNumberOfSides();

}
