package com.pmz.qwixx.game.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class MainActivity extends AppCompatActivity {

    BootstrapButton playButton;
    BootstrapButton rulesButton;
    BootstrapButton exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideNavigationBar();

        setupButtons();


    }
	
	@Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }

    private void setupButtons(){
        playButton = (BootstrapButton) findViewById(R.id.playBtn);
        rulesButton = (BootstrapButton) findViewById(R.id.rulesBtn);
        exitButton = (BootstrapButton) findViewById(R.id.exitBtn);

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start QwixxSimpleGameActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        QwixxSimpleGameActivity.class);
                startActivity(myIntent);
            }
        });

        rulesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start QwixxSimpleGameActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        RulesActivity.class);
                startActivity(myIntent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finishAffinity();
            }
        });
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
