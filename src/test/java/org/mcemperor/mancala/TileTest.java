package org.mcemperor.mancala;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest {

    @Test
    public void testAddGemsMethodWithPositiveAmount() {
        var tile = new Tile(7);
        tile.addGems(4);
        var actual = tile.getGems();
        var expected = 11;
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveAllGemsMethod() {
        var tile = new Tile(7);
        tile.removeAllGems();
        var expected = 0;
        var actual = tile.getGems();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetGemsMethod() {
        var expected = 7;
        var actual = new Tile(7).getGems();
        assertEquals(expected, actual);
    }
}
