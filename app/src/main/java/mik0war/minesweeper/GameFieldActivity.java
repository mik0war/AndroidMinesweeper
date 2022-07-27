package mik0war.minesweeper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import mik0war.minesweeper.minesweeperfield.MinesweeperFieldFragment;
import mik0war.minesweeper.minesweeperfield.states.GameState;

public class GameFieldActivity extends AppCompatActivity {

    private GameState gameState;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_field_activity);

        createButton = (Button) findViewById(R.id.createField);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction().add(R.id.fragmentContainerView,
                        MinesweeperFieldFragment.class, null)
                        .commit();
            }
        });

    }
}
