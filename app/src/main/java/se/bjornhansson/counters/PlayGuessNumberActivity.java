package se.bjornhansson.counters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * The activity used when playing the game "guess the number".
 */
public class PlayGuessNumberActivity extends AppCompatActivity implements View.OnKeyListener {

    private final Game myGame = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_guess_number);

        TextView textView = (TextView) findViewById(R.id.playGameHeadline);
        if (textView == null) {
            throw new RuntimeException("Headline textView should not be null.");
        }
        textView.setText(getString(R.string.headline_what_is_number));

        GridView gridView = (GridView) findViewById(R.id.gameBoard);
        if (gridView == null) {
            throw new RuntimeException("gridView should not be null.");
        }
        GridAdapter gridAdapter = new GridAdapter(this, getCounterList());
        gridView.setAdapter(gridAdapter);

        EditText editText = (EditText) findViewById(R.id.guessNumber);
        if (editText == null) {
            throw new RuntimeException("editText should not be null.");
        }
        editText.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                keyCode == EditorInfo.IME_ACTION_DONE ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            String toastMessage = getString(R.string.no_match);
            EditText editText = (EditText) view;
            String editTextValue = editText.getText().toString();
            if (!editTextValue.isEmpty() && myGame.getMyTargetNumber() == Integer.parseInt(editTextValue)) {
                toastMessage = getString(R.string.match);
            }
            Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        return false; // pass on to other listeners.
    }

    private ArrayList<String> getCounterList() {
        ArrayList<String> list = new ArrayList<>();
        int targetNumber = myGame.getMyTargetNumber();
        int numberOfHigh = (int)(targetNumber * 0.01);
        int numberOfMedium = (int)((targetNumber % 100) * 0.1);
        int numberOfLow = targetNumber % 10;

        for (int i = 0; i < numberOfHigh; i++) {
            list.add(getString(R.string.high_nr));
        }
        for (int i = 0; i < numberOfMedium; i++) {
            list.add(getString(R.string.medium_nr));
        }
        for (int i = 0; i < numberOfLow; i++) {
            list.add(getString(R.string.low_nr));
        }

        return list;
    }
}
