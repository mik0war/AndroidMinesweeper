package mik0war.minesweeper.minesweeperfield;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MinesweeperFieldFragment extends Fragment {

    private Context context;
    private MinesweeperFieldController gameController;
    private MinesweeperFieldView viewController;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        context = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int fieldSize = 10;
        int bombCount = 10;

        LinearLayout field = new LinearLayout(super.getContext());
        field.setOrientation(LinearLayout.VERTICAL);

        gameController = new MinesweeperFieldController(context, fieldSize, bombCount);
        viewController = new MinesweeperFieldView(context);

        for (int rowNumber = 0; rowNumber < fieldSize; rowNumber++){
            LinearLayout row = new LinearLayout(super.getContext());
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            row.setOrientation(LinearLayout.HORIZONTAL);

            for (int columnNumber = 0; columnNumber < fieldSize; columnNumber++){
                ImageView button = MinesweeperFieldView.generateButton(
                        columnNumber + rowNumber * fieldSize, gameController);
                viewController.addButton(button);
                row.addView(button);
            }

            gameController.setMinesweeperFieldView(viewController);

            field.addView(row);
        }

        return field;
    }


}