package mik0war.minesweeper.minesweeperfield;


import java.util.ArrayList;
import java.util.Random;

import mik0war.minesweeper.minesweeperfield.states.BombState;

public class MinesweeperFieldData {
    private final ArrayList<MinesweeperCell> minesweeperCells;
    private final int fieldSize;
    private final int bombCount;

    public MinesweeperFieldData(int fieldSize, int bombCount, int startPosition) {
        this.fieldSize = fieldSize;
        this.bombCount = bombCount;

        minesweeperCells = new ArrayList<>();

        for(int position  = 0; position < fieldSize*fieldSize; position++)
            minesweeperCells.add(new MinesweeperCell(position));

        setUpGame(startPosition);
    }

    public MinesweeperFieldData(int fieldSize, int bombCount) {
        this.fieldSize = fieldSize;
        this.bombCount = bombCount;

        minesweeperCells = new ArrayList<>();

        for(int position  = 0; position < fieldSize*fieldSize; position++)
            minesweeperCells.add(new MinesweeperCell(position));

    }

    public MinesweeperCell getCell (int index){
        return minesweeperCells.get(index);
    }

    private void setUpGame(int startPosition){
        bombGenerator(startPosition);

        for (int position = 0; position < minesweeperCells.size(); position++){
            minesweeperCells.get(position).setNearbyBombsCount(getNearbyCellsList(position));
        }
    }

    private void bombGenerator(int startPosition){
        Random random = new Random();
        MinesweeperCell currentMinesweeperCell;
        MinesweeperFieldData techField = new MinesweeperFieldData(this.fieldSize, this.bombCount);

        for (int i = 0; i < bombCount;){
            int nextCellPosition = random.nextInt(minesweeperCells.size());

            if (nextCellPosition != startPosition &&
                    !techField.getNearbyCellsIndexes(startPosition).contains(nextCellPosition))
                currentMinesweeperCell = minesweeperCells.get(nextCellPosition);
            else
                continue;

            if (currentMinesweeperCell.getCurrentState() != BombState.BOMB) {
                currentMinesweeperCell.setCurrentState(BombState.BOMB);
                i++;
            }
        }
    }

    public ArrayList<Integer> getNearbyCellsIndexes(int position){
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int cellPos = position-fieldSize; cellPos <= position+fieldSize; cellPos += fieldSize)
            for (int changeX = -1; changeX <= 1; changeX++)
                if (cellPos >= 0 && cellPos < fieldSize*fieldSize &&
                        !(position % fieldSize == 0 && changeX < 0) &&
                        !(position % fieldSize == fieldSize-1 && changeX > 0) &&
                        !(position == cellPos + changeX))
                    indexes.add(cellPos + changeX);

        return indexes;
    }

    public ArrayList<MinesweeperCell> getNearbyCellsList (int position){
        ArrayList<MinesweeperCell> nearbyMinesweeperCells = new ArrayList<>();
        for (int index: getNearbyCellsIndexes(position) ) {
            nearbyMinesweeperCells.add(minesweeperCells.get(index));
        }

        return nearbyMinesweeperCells;
    }

}
