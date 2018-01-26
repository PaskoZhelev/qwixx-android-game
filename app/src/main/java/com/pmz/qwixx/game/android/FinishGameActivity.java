package com.pmz.qwixx.game.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapButtonGroup;

public class FinishGameActivity extends AppCompatActivity {

    BootstrapButton mainMenuButton;
    TextView player1Score;
    TextView player2Score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_game_activity);

        hideNavigationBar();
        setupButton();
        setupTextViews();
        printResults();
    }

    private void printResults() {
        Intent currIntent = getIntent();
        player1Score.setText(currIntent.getStringExtra("resultPlayer1"));
        player2Score.setText(currIntent.getStringExtra("resultPlayer2"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }

    private void setupButton(){
        mainMenuButton = findViewById(R.id.mainMenuBtn);

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start QwixxSimpleGameActivity.class
                Intent myIntent = new Intent(FinishGameActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void setupTextViews(){
        player1Score = findViewById(R.id.player1Score);
        player2Score = findViewById(R.id.player2Score);

    }

    private void hideNavigationBar() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
