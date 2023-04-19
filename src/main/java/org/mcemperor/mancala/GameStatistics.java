package org.mcemperor.mancala;

import java.util.List;

public class GameStatistics {

    private final int winningPlayer;

    private final List<Integer> pointsPerPlayer;

    public GameStatistics(List<Integer> pointsPerPlayer) {
        this.pointsPerPlayer = List.copyOf(pointsPerPlayer);
        int indexOfHighest = -1;
        for (int i = 0; i < this.pointsPerPlayer.size(); i++) {
            if (indexOfHighest == -1 || this.pointsPerPlayer.get(i) > this.pointsPerPlayer.get(indexOfHighest)) {
                indexOfHighest = i;
            }
        }
        this.winningPlayer = indexOfHighest;
    }

    public int getWinningPlayer() {
        return winningPlayer;
    }

    public int getPointsOf(int player) {
        return pointsPerPlayer.get(player);
    }
}
