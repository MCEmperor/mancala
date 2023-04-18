package org.mcemperor.mancala;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultOppositeTileTest {

    @Test
    public void testGetOppositeTiles() {
        var board = new DefaultBoard(2, 6, 4);
        var opp = new DefaultOppositeTileStrategy();

        BiFunction<Integer, Integer, List<Tile>> tile = (a, b) -> List.of(board.getSide(a).getTile(b));
        BiFunction<Integer, Integer, List<Tile>> oppo = (a, b) -> opp.getOppositeTiles(board, a, b);

        assertEquals(tile.apply(1, 5), oppo.apply(0, 0));
        assertEquals(tile.apply(0, 0), oppo.apply(1, 5));
        assertEquals(tile.apply(1, 3), oppo.apply(0, 2));
        assertEquals(tile.apply(0, 2), oppo.apply(1, 3));

    }
}
