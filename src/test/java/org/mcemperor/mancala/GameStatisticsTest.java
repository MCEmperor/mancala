package org.mcemperor.mancala;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStatisticsTest {

    @Test
    public void testConstructor() {
        List<Integer> points = List.of(3, 11, 7);

        var stats = new GameStatistics(points);

        assertEquals(1, stats.getWinningPlayer());
    }

    @Test
    public void testConstructorWithEqualPoints() {
        List<Integer> points = List.of(7, 7);

        var stats = new GameStatistics(points);

        assertEquals(0, stats.getWinningPlayer());
    }
}
