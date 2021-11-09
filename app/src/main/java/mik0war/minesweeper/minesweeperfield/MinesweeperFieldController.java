package mik0war.minesweeper.minesweeperfield;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import mik0war.minesweeper.minesweeperfield.states.ClickState;
import mik0war.minesweeper.minesweeperfield.states.GameState;
import mik0war.minesweeper.minesweeperfield.states.BombState;

public class MinesweeperFieldController implements View.OnClickListener, View.OnLongClickListener {
    private static Context context;
    private MinesweeperFieldView minesweeperFieldView;

    private MinesweeperFieldData gameField;
    private GameState gameState;

    private int fieldSize;
    private int bombCount;

    private int nonClickCellsLeft;
    private boolean isFirstMove;

    public MinesweeperFieldController(Context context, int fieldSize, int bombCount) {
        MinesweeperFieldController.context = context;

        this.gameField = new MinesweeperFieldData(fieldSize, bombCount, 0, new ArrayList<>());
        this.isFirstMove = true;

        this.fieldSize = fieldSize;
        this.bombCount = bombCount;
        this.gameState = GameState.ONGOING;
        this.nonClickCellsLeft = fieldSize*fieldSize - bombCount;
    }

    public void setMinesweeperFieldView(MinesweeperFieldView minesweeperFieldView) {
        this.minesweeperFieldView = minesweeperFieldView;
    }

    private void clickNearbyButtons(ArrayList<Integer> nearbyIndexes){
        for (int pos : nearbyIndexes)
            if (gameField.getCell(pos).getClickState() == ClickState.COMMON)
                performClickButton(pos);
    }

    private void performClickButton(int pos){
        ImageButton btn = minesweeperFieldView.getButton(pos);
        btn.performClick();
    }

    @Override
    public boolean onLongClick(View v) {
        MinesweeperCell currentMinesweeperCell = gameField.getCell(v.getId());

        currentMinesweeperCell.changeFlag();
        minesweeperFieldView.setFlag(currentMinesweeperCell);
        return true;
    }

    @Override
    public void onClick(View v) {
        digCell(v);
    }


    private void digCell(View v){
        if (isFirstMove) {
            while (true) {
                if (gameField.getCell(v.getId()).getCurrentState() != BombState.BOMB &&
                        gameField.getCell(v.getId()).getNearbyBombsCount() == 0) {
                    isFirstMove = false;
                    break;
                }
                else
                    this.gameField = new MinesweeperFieldData(
                            fieldSize, bombCount, v.getId(), gameField.getFlagsIndexes());
            }
        }

        MinesweeperCell currentCell = gameField.getCell(v.getId());
        makeMove(currentCell);
    }

    private void changeGameState(GameState newGameState){
        this.gameState = newGameState;
    }

    public void cleanField(){
        for (int cellId = 0; cellId < fieldSize*fieldSize; cellId++){
            MinesweeperCell currentCell = gameField.getCell(cellId);
            clickCell(currentCell);
        }
    }

    private void clickCell(MinesweeperCell currentCell) {
        if (currentCell.getClickState() != ClickState.CLICK) {
            minesweeperFieldView.clickButton(currentCell, gameState);
            currentCell.setClickState(ClickState.CLICK);
        }
    }


    private void makeMove(MinesweeperCell cell){
        if (cell.getClickState() == ClickState.CLICK)
            if (cell.getNearbyBombsCount() == cell.getNearbyFlagsCount(
                    gameField.getNearbyCellsList(cell.getPosition())))
                clickNearbyButtons(gameField.getNearbyCellsIndexes(cell.getPosition()));

        if (cell.getClickState() == ClickState.COMMON){
            if (cell.getCurrentState() != BombState.BOMB)
                nonClickCellsLeft--;
            cell.setClickState(ClickState.CLICK);
            minesweeperFieldView.setDrawable(cell, gameState);
            if (cell.getNearbyBombsCount() == 0)
                clickNearbyButtons(gameField.getNearbyCellsIndexes(cell.getPosition()));
        }

        if (cell.getCurrentState() == BombState.BOMB &&
                cell.getClickState() != ClickState.FLAG){
            clickCell(cell);
            changeGameState(GameState.LOSE);
            cleanField();

        }

        if (nonClickCellsLeft == 0){
            changeGameState(GameState.WIN);
            cleanField();
        }
    }
}
