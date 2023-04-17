package org.mcemperor.mancala;

import java.util.List;
import java.util.stream.Stream;

public class Board {

    private final int tilesPerPlayer;

    private final List<BoardSide> sides;

    public Board(int tilesPerPlayer, int startGems) {
        this.tilesPerPlayer = tilesPerPlayer;
        this.sides = Stream.generate(() -> new BoardSide(tilesPerPlayer, startGems))
            .limit(2)
            .toList();
    }

    public BoardSide getSide(int player) {
        return sides.get(player);
    }

    public int tilesPerPlayer() {
        return tilesPerPlayer;
    }

    public int sides() {
        return sides.size();
    }
}
