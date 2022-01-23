package mik0war.minesweeper.minesweeperfield.textureIds;

import mik0war.minesweeper.R;

public enum CommonTextureState {
    CELL_0(R.drawable.empty_cell),
    CELL_1(R.drawable.cell_1),
    CELL_2(R.drawable.cell_2),
    CELL_3(R.drawable.cell_3),
    CELL_4(R.drawable.cell_4),
    CELL_5(R.drawable.cell_5),
    CELL_6(R.drawable.cell_6),
    CELL_7(R.drawable.cell_7),
    CELL_8(R.drawable.cell_8),
    EMPTY_CELL(R.drawable.empty_cell),
    COMMON_CELL(R.drawable.cell),
    FLAG(R.drawable.flag),
    ;

    int texture;

    public int getTexture(){
        return texture;
    }

    CommonTextureState(int texture) {
        this.texture = texture;
    }
}
