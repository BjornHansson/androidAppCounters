package se.bjornhansson.counters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * The menu activity, visible when starting the app.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default Android stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /**
     * Called when the user clicks the button.
     */
    public void playBuildNumber(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PlayBuildNumberActivity.class);
        // Activities needs to be defined in the manifest file
        startActivity(intent);
    }

    public void playGuessNumber(View view) {
        Intent intent = new Intent(this, PlayGuessNumberActivity.class);
        startActivity(intent);
    }
}