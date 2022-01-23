package mik0war.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import mik0war.minesweeper.minesweeperfield.MinesweeperFieldFragment;
import mik0war.minesweeper.minesweeperfield.states.GameState;

public class MainActivity extends AppCompatActivity {

    private GameState gameState;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createButton = (Button) findViewById(R.id.createField);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction().add(R.id.fragmentContainerView, MinesweeperFieldFragment.class, null).commit();
            }
        });

    }
    
    public void changeGameState(GameState gameState){
        this.gameState = gameState;
    }
}