package mik0war.minesweeper.minesweeperfield.textureIds;

import mik0war.minesweeper.R;

public enum BombTextureState {
    BOMB(R.drawable.bomb_normal),
    BOMB_EXPLODED(R.drawable.bomb_exploded),
    BOMB_MISTAKE(R.drawable.bomb_mistake),
    BOMB_FLAGGED(R.drawable.bomb_flagged),
    BOMB_WIN(R.drawable.bomb_win),
    ;

    int texture;

    public int getTexture(){
        return texture;
    }

    BombTextureState(int texture) {
        this.texture = texture;
    }
}
