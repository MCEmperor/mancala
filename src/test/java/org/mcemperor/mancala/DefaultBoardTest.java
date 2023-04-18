package org.mcemperor.mancala;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultBoardTest {

    @Test
    public void testDistributeGemsMethod() {
        DefaultBoard board = new DefaultBoard(2, 6, 4);

        int[][] expectedGems = {
            { 5, 5, 5, 5, 5, 5 }, // Player 1
            { 5, 4, 4, 4, 4, 5 }, // Player 2
        };
        int expectedGemsAtMancalaPlayer1 = 0;
        int expectedGemsAtMancalaPlayer2 = 1;

        DistributionResult result = board.distributeGems(1, 5, 9);

        for (int player = 0; player < 2; player++) {
            for (int tile = 0; tile < 6; tile++) {
                int expected = expectedGems[player][tile];
                int actual = board.getSide(player).getTile(tile).getGems();
                assertEquals(expected, actual, "Gems for player " + (player + 1) + ", tile " + tile);
            }
        }
        assertEquals(expectedGemsAtMancalaPlayer1, board.getSide(0).getMancala().getGems());
        assertEquals(expectedGemsAtMancalaPlayer2, board.getSide(1).getMancala().getGems());

        assertEquals(9, result.gemsDistributed());
        assertEquals(1, result.lastTileBoardSideIndex());
        assertEquals(0, result.lastTileIndex());
        assertEquals(1, result.gemsDistributedAtLastTile());
    }
}
