package mik0war.minesweeper.minesweeperfield;

import java.util.ArrayList;

import mik0war.minesweeper.minesweeperfield.states.ClickState;
import mik0war.minesweeper.minesweeperfield.states.BombState;

public class MinesweeperCell {
    private BombState currentBombState;
    private int nearbyBombsCount;
    private ClickState clickState;

    private final int position;

    public MinesweeperCell(int position) {
        this.currentBombState = BombState.COMMON;
        this.clickState = ClickState.COMMON;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public BombState getCurrentState() {
        return currentBombState;
    }

    public void setCurrentState(BombState currentBombState) {
        this.currentBombState = currentBombState;
    }

    public int getNearbyBombsCount() {
        return nearbyBombsCount;
    }

    public void setNearbyBombsCount(ArrayList<MinesweeperCell> nearbyMinesweeperCells) {
        int nearbyBombs = 0;
        for (MinesweeperCell minesweeperCell : nearbyMinesweeperCells) {
            if (minesweeperCell.getCurrentState() == BombState.BOMB)
                nearbyBombs++;
        }
        this.nearbyBombsCount = nearbyBombs;
    }

    public int getNearbyFlagsCount(ArrayList<MinesweeperCell> nearbyMinesweeperCells) {
        int nearbyFlags = 0;
        for (MinesweeperCell minesweeperCell : nearbyMinesweeperCells) {
            if (minesweeperCell.getClickState() == ClickState.FLAG)
                nearbyFlags++;
        }
        return nearbyFlags;
    }

    public ClickState getClickState() {
        return clickState;
    }

    public void setClickState(ClickState clickState) {
        this.clickState = clickState;
    }

    public void changeFlag(){
        switch (this.clickState){
            case FLAG:
                this.clickState = ClickState.COMMON;
                break;
            case COMMON:
                this.clickState = ClickState.FLAG;
                break;
        }
    }
}
