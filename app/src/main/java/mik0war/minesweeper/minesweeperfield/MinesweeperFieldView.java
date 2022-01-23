package mik0war.minesweeper.minesweeperfield;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import mik0war.minesweeper.minesweeperfield.states.BombState;
import mik0war.minesweeper.minesweeperfield.textureIds.BombTextureState;
import mik0war.minesweeper.minesweeperfield.states.ClickState;
import mik0war.minesweeper.minesweeperfield.textureIds.CommonTextureState;
import mik0war.minesweeper.minesweeperfield.states.GameState;

public class MinesweeperFieldView {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    ArrayList<ImageView> buttons;

    public MinesweeperFieldView(Context context) {
        MinesweeperFieldView.context = context;
        this.buttons = new ArrayList<>();
    }

    public void addButton(ImageView button){
        buttons.add(button);
    }

    public static ImageView generateButton(int btnId, MinesweeperFieldController gameController){
        ImageView button = new ImageView(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        button.setId(btnId);
        button.setClickable(true);

        button.setBackgroundResource(CommonTextureState.COMMON_CELL.getTexture());
        button.setScaleType(ImageView.ScaleType.CENTER_CROP);
        button.setPadding(0,0,0,0);

        button.setOnClickListener(gameController);
        button.setOnLongClickListener(gameController);

        return button;
    }

    public ImageView getButton(int id) {
        return buttons.get(id);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static Drawable getDrawable(int resId){
        return context.getDrawable(resId);
    }

    public void setDrawable(MinesweeperCell cell, GameState gameState){
        ImageView btn = buttons.get(cell.getPosition());
        btn.setImageDrawable(getDrawable(setTexture(cell, gameState)));
    }
    public int setTexture(MinesweeperCell minesweeperCell, GameState gameState){
        if (minesweeperCell.getCurrentState() == BombState.COMMON)
            return setCommonTexture(minesweeperCell, gameState);
        else
            return setBombTexture(minesweeperCell, gameState);
    }

    private int setCommonTexture(MinesweeperCell minesweeperCell, GameState gameState){
        if (gameState == GameState.LOSE)
            if (minesweeperCell.getClickState() == ClickState.FLAG &&
                    minesweeperCell.getCurrentState() == BombState.COMMON)
                return BombTextureState.BOMB_MISTAKE.getTexture();
        return CommonTextureState.valueOf("CELL_" +
                minesweeperCell.getNearbyBombsCount()).getTexture();
    }

    private int setBombTexture(MinesweeperCell cell, GameState gameState) {
        switch (gameState){
            case ONGOING:
                return BombTextureState.BOMB_EXPLODED.getTexture();
            case WIN:
                return BombTextureState.BOMB_WIN.getTexture();
            case LOSE:
                if (cell.getClickState() == ClickState.FLAG)
                    return BombTextureState.BOMB_FLAGGED.getTexture();
            default:
                return BombTextureState.BOMB.getTexture();
        }
    }

    public void setFlag(MinesweeperCell currentMinesweeperCell){
        if (currentMinesweeperCell.getClickState() != ClickState.CLICK){
            ImageView btn = buttons.get(currentMinesweeperCell.getPosition());
            int flagTexture = changeFlag(currentMinesweeperCell);

            btn.setImageDrawable(getDrawable(flagTexture));
        }
    }

    public int changeFlag(MinesweeperCell minesweeperCell){
        if (minesweeperCell.getClickState() == ClickState.FLAG) {
            return CommonTextureState.FLAG.getTexture();
        }
        else {
            return CommonTextureState.COMMON_CELL.getTexture();
        }
    }
    
    public void clickButton(MinesweeperCell currentCell, GameState gameState) {
        ImageView currentButton = buttons.get(currentCell.getPosition());
        currentButton.setImageDrawable(getDrawable(setTexture(currentCell, gameState)));
    }
}
