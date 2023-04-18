package org.mcemperor.mancala;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
public class Tile {

    private int gems;

    public Tile(int gems) {
        this.gems = gems;
    }

    public void addGems(int amount) {
        this.gems += amount;
    }

    public int removeAllGems() {
        int oldNumberOfGems = gems;
        gems = 0;
        return oldNumberOfGems;
    }

    public int getGems() {
        return gems;
    }
}
