package se.bjornhansson.counters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * The activity used when playing the game "build the number".
 */
public class PlayBuildNumberActivity extends AppCompatActivity {

    private final Game myGame = new Game();
    private GridAdapter myGridAdapter;
    private GridView myGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_build_number);

        String headline = getString(R.string.headline_build_number) + " " + myGame.getMyTargetNumber();
        TextView textView = (TextView) findViewById(R.id.playGameHeadline);
        if (textView == null) {
            throw new RuntimeException("Headline textView should not be null.");
        }
        textView.setText(headline);

        myGridAdapter = new GridAdapter(this, new ArrayList<String>());
        myGridView = (GridView) findViewById(R.id.gameBoard);
        if (myGridView == null) {
            throw new RuntimeException("gridView should not be null.");
        }
        myGridView.setAdapter(myGridAdapter);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String toastMessage = getString(R.string.removed_counter) + " " + ((TextView) v.findViewById(R.id.grid_item)).getText();
                Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                myGridAdapter.removeItem(position);
                updateGridAndValidateScore();
            }
        });
    }

    public void addHigh(View view) {
        myGridAdapter.add(getString(R.string.high_nr));
        updateGridAndValidateScore();
    }

    public void addMedium(View view) {
        myGridAdapter.add(getString(R.string.medium_nr));
        updateGridAndValidateScore();
    }

    public void addLow(View view) {
        myGridAdapter.add(getString(R.string.low_nr));
        updateGridAndValidateScore();
    }

    private void updateGridAndValidateScore() {
        myGridView.setAdapter(myGridAdapter);
        myGridAdapter.notifyDataSetChanged();

        int currentNumber = 0;
        for (int i = 0; i < myGridAdapter.getCount(); i++) {
            currentNumber += Integer.parseInt(myGridAdapter.getItem(i));
        }
        if (myGame.getMyTargetNumber() == currentNumber) {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.match), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
