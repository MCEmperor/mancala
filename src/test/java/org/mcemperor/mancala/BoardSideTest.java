package org.mcemperor.mancala;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardSideTest {

    @Test
    public void testPeekGemsAtMancalaMethod() {
        var boardSide = new BoardSide(7, 3);
        var expected = 0;
        var actual = boardSide.peekGemsAtMancala();
        assertEquals(expected, actual);
    }

    @Test
    public void testDistributeGemsMethodWithIncludeMancala() {
        var boardSide = new BoardSide(6, 4);
        int index = 2;

        int expectedGemsDistributed = 4;
        boolean expectedLastTileWasMancala = false;

        var result = boardSide.distributeGems(4, index, true);
        assertEquals(expectedGemsDistributed, result.gemsDistributed());
        assertEquals(expectedLastTileWasMancala, result.lastTileIndex() == -1);

        int[] expectedTiles = { 4, 4, 5, 5, 5, 5 };
        for (int i = 0; i < 6; i++) {
            assertEquals(expectedTiles[i], boardSide.peekGems(i));
        }
        assertEquals(0, boardSide.peekGemsAtMancala());
    }

    @Test
    public void testDistributeGemsMethodWithIncludeMancalaAndOverflow() {
        var boardSide = new BoardSide(6, 4);
        int index = 3;

        int expectedGemsDistributed = 4;
        boolean expectedLastTileWasMancala = true;

        var result = boardSide.distributeGems(4, index, true);
        assertEquals(expectedGemsDistributed, result.gemsDistributed());
        assertEquals(expectedLastTileWasMancala, result.lastTileIndex() == -1);

        int[] expectedTiles = { 4, 4, 4, 5, 5, 5 };
        for (int i = 0; i < 6; i++) {
            assertEquals(expectedTiles[i], boardSide.peekGems(i));
        }
        assertEquals(1, boardSide.peekGemsAtMancala());
    }

    @Test
    public void testDistributeGemsMethodWithExcludeMancalaAndOverflow() {
        var boardSide = new BoardSide(6, 4);
        int index = 3;

        int expectedGemsDistributed = 3;
        boolean expectedLastTileWasMancala = false;

        var result = boardSide.distributeGems(4, index, false);
        assertEquals(expectedGemsDistributed, result.gemsDistributed());
        assertEquals(expectedLastTileWasMancala, result.lastTileIndex() == -1);

        int[] expectedTiles = { 4, 4, 4, 5, 5, 5 };
        for (int i = 0; i < 6; i++) {
            assertEquals(expectedTiles[i], boardSide.peekGems(i));
        }
        assertEquals(0, boardSide.peekGemsAtMancala());
    }
}
