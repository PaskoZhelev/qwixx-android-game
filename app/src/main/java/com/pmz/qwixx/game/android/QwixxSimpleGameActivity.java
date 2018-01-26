package com.pmz.qwixx.game.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapButtonGroup;
import com.pmz.qwixx.game.android.constant.QwixxConstants;
import com.pmz.qwixx.game.android.enums.ButtonGroupColor;
import com.pmz.qwixx.game.android.utils.DiceRoller;

public class QwixxSimpleGameActivity extends AppCompatActivity {

    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

    private DiceRoller diceRoller;
    private  AlertDialog.Builder dialogBuilder;

    BootstrapButton finishButton;

    BootstrapButtonGroup redButtonGroup1_1;
    BootstrapButtonGroup redButtonGroup1_2;
    BootstrapButtonGroup yellowButtonGroup1_1;
    BootstrapButtonGroup yellowButtonGroup1_2;
    BootstrapButtonGroup greenButtonGroup1_1;
    BootstrapButtonGroup greenButtonGroup1_2;
    BootstrapButtonGroup blueButtonGroup1_1;
    BootstrapButtonGroup blueButtonGroup1_2;

    BootstrapButtonGroup redButtonGroup2_1;
    BootstrapButtonGroup redButtonGroup2_2;
    BootstrapButtonGroup yellowButtonGroup2_1;
    BootstrapButtonGroup yellowButtonGroup2_2;
    BootstrapButtonGroup greenButtonGroup2_1;
    BootstrapButtonGroup greenButtonGroup2_2;
    BootstrapButtonGroup blueButtonGroup2_1;
    BootstrapButtonGroup blueButtonGroup2_2;

    BootstrapButtonGroup mistakesGroup1;
    BootstrapButtonGroup mistakesGroup2;

    ImageView redDieView;
    ImageView yellowDieView;
    ImageView greenDieView;
    ImageView blueDieView;
    ImageView whiteDieView1;
    ImageView whiteDieView2;

    LinearLayout diceLayout;

    private int redCrossesPlayer1;
    private int yellowCrossesPlayer1;
    private int greenCrossesPlayer1;
    private int blueCrossesPlayer1;
    private int mistakesPlayer1;

    private int redCrossesPlayer2;
    private int yellowCrossesPlayer2;
    private int greenCrossesPlayer2;
    private int blueCrossesPlayer2;
    private int mistakesPlayer2;

    private int resultPlayer1;
    private int resultPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_game_activity);
        diceRoller = new DiceRoller();

        hideNavigationBar();

        setupButtonGroups();
        setupFinishButton();
        setupDiceViews();
        setupDiceLayout();
    }
	
	@Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }

    @Override
    public void onBackPressed() {
        setupAlertDialog(false);
        showAlertDialog();
    }


    private int getDrawableDiceId(String color, int number){
        String name = color + "die" + number;
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }

    private void rollDice(){
        diceRoller.rollDice();
        changeDiceImages();
    }

    private void changeDiceImages() {
        redDieView.setImageResource(getDrawableDiceId(
                QwixxConstants.RED_COLOR, diceRoller.getRedDie()));
        yellowDieView.setImageResource(getDrawableDiceId(
                QwixxConstants.YELLOW_COLOR, diceRoller.getYellowDie()));
        greenDieView.setImageResource(getDrawableDiceId(
                QwixxConstants.GREEN_COLOR, diceRoller.getGreenDie()));
        blueDieView.setImageResource(getDrawableDiceId(
                QwixxConstants.BLUE_COLOR, diceRoller.getBlueDie()));
        whiteDieView1.setImageResource(getDrawableDiceId(
                QwixxConstants.WHITE_COLOR, diceRoller.getWhiteDie1()));
        whiteDieView2.setImageResource(getDrawableDiceId(
                QwixxConstants.WHITE_COLOR, diceRoller.getWhiteDie2()));
    }

    private void calculateResults() {
        resultPlayer1 = calculatePointsBasedOnCrosses(redCrossesPlayer1) +
                        calculatePointsBasedOnCrosses(yellowCrossesPlayer1) +
                        calculatePointsBasedOnCrosses(greenCrossesPlayer1) +
                        calculatePointsBasedOnCrosses(blueCrossesPlayer1) -
                        (mistakesPlayer1 * 5);

        resultPlayer2 = calculatePointsBasedOnCrosses(redCrossesPlayer2) +
                calculatePointsBasedOnCrosses(yellowCrossesPlayer2) +
                calculatePointsBasedOnCrosses(greenCrossesPlayer2) +
                calculatePointsBasedOnCrosses(blueCrossesPlayer2) -
                (mistakesPlayer2 * 5);
    }

    private void calculatePlayersCrosses() {
        calculateCrossesOfButtonGroup(redButtonGroup1_1, ButtonGroupColor.RED, PLAYER_ONE);
        calculateCrossesOfButtonGroup(redButtonGroup1_2, ButtonGroupColor.RED, PLAYER_ONE);
        calculateCrossesOfButtonGroup(yellowButtonGroup1_1, ButtonGroupColor.YELLOW, PLAYER_ONE);
        calculateCrossesOfButtonGroup(yellowButtonGroup1_2, ButtonGroupColor.YELLOW, PLAYER_ONE);
        calculateCrossesOfButtonGroup(greenButtonGroup1_1, ButtonGroupColor.GREEN, PLAYER_ONE);
        calculateCrossesOfButtonGroup(greenButtonGroup1_2, ButtonGroupColor.GREEN, PLAYER_ONE);
        calculateCrossesOfButtonGroup(blueButtonGroup1_1, ButtonGroupColor.BLUE, PLAYER_ONE);
        calculateCrossesOfButtonGroup(blueButtonGroup1_2, ButtonGroupColor.BLUE, PLAYER_ONE);
        calculateCrossesOfButtonGroup(mistakesGroup1, ButtonGroupColor.MISTAKES, PLAYER_ONE);

        calculateCrossesOfButtonGroup(redButtonGroup2_1, ButtonGroupColor.RED, PLAYER_TWO);
        calculateCrossesOfButtonGroup(redButtonGroup2_2, ButtonGroupColor.RED, PLAYER_TWO);
        calculateCrossesOfButtonGroup(yellowButtonGroup2_1, ButtonGroupColor.YELLOW, PLAYER_TWO);
        calculateCrossesOfButtonGroup(yellowButtonGroup2_2, ButtonGroupColor.YELLOW, PLAYER_TWO);
        calculateCrossesOfButtonGroup(greenButtonGroup2_1, ButtonGroupColor.GREEN, PLAYER_TWO);
        calculateCrossesOfButtonGroup(greenButtonGroup2_2, ButtonGroupColor.GREEN, PLAYER_TWO);
        calculateCrossesOfButtonGroup(blueButtonGroup2_1, ButtonGroupColor.BLUE, PLAYER_TWO);
        calculateCrossesOfButtonGroup(blueButtonGroup2_2, ButtonGroupColor.BLUE, PLAYER_TWO);
        calculateCrossesOfButtonGroup(mistakesGroup2, ButtonGroupColor.MISTAKES, PLAYER_TWO);

    }

    private void calculateCrossesOfButtonGroup(BootstrapButtonGroup buttonGroup, ButtonGroupColor buttonGroupColor, int player) {
        for(int i = 0; i < buttonGroup.getChildCount(); i++) {
            if(buttonGroup.getChildAt(i).isSelected()){
                increaseCrossesBasedOnColorGroup(buttonGroupColor, player);
            }
        }
    }

    private void increaseCrossesBasedOnColorGroup(ButtonGroupColor buttonGroupColor, int player) {
        switch (buttonGroupColor){
            case RED:
                if(player == PLAYER_ONE){
                    redCrossesPlayer1++;
                } else{
                    redCrossesPlayer2++;
                }
                break;
            case YELLOW:
                if(player == PLAYER_ONE){
                    yellowCrossesPlayer1++;
                } else{
                    yellowCrossesPlayer2++;
                }
                break;
            case GREEN:
                if(player == PLAYER_ONE){
                    greenCrossesPlayer1++;
                } else{
                    greenCrossesPlayer2++;
                }
                break;
            case BLUE:
                if(player == PLAYER_ONE){
                    blueCrossesPlayer1++;
                } else{
                    blueCrossesPlayer2++;
                }
                break;

            default:
                if(player == PLAYER_ONE){
                    mistakesPlayer1++;
                } else{
                    mistakesPlayer2++;
                }
                break;
        }
    }

    private int calculatePointsBasedOnCrosses(int numCrosses) {
        switch(numCrosses){
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 6;
            case 4:
                return 10;
            case 5:
                return 15;
            case 6:
                return 21;
            case 7:
                return 28;
            case 8:
                return 36;
            case 9:
                return 45;
            case 10:
                return 55;
            case 11:
                return 66;
            case 12:
                return 78;

            default:
                return 0;
        }
    }

    private void showAlertDialog() {
        AlertDialog alert = dialogBuilder.create();
        alert.show();
    }

    private void setupButtonGroups() {
        redButtonGroup1_1 = findViewById(R.id.redGroup1_1);
        redButtonGroup1_2 = findViewById(R.id.redGroup1_2);
        yellowButtonGroup1_1 = findViewById(R.id.yellowGroup1_1);
        yellowButtonGroup1_2 = findViewById(R.id.yellowGroup1_2);
        greenButtonGroup1_1 = findViewById(R.id.greenGroup1_1);
        greenButtonGroup1_2 = findViewById(R.id.greenGroup1_2);
        blueButtonGroup1_1 = findViewById(R.id.blueGroup1_1);
        blueButtonGroup1_2 = findViewById(R.id.blueGroup1_2);

        redButtonGroup2_1 = findViewById(R.id.redGroup2_1);
        redButtonGroup2_2 = findViewById(R.id.redGroup2_2);
        yellowButtonGroup2_1 = findViewById(R.id.yellowGroup2_1);
        yellowButtonGroup2_2 = findViewById(R.id.yellowGroup2_2);
        greenButtonGroup2_1 = findViewById(R.id.greenGroup2_1);
        greenButtonGroup2_2 = findViewById(R.id.greenGroup2_2);
        blueButtonGroup2_1 = findViewById(R.id.blueGroup2_1);
        blueButtonGroup2_2 = findViewById(R.id.blueGroup2_2);

        mistakesGroup1 = findViewById(R.id.mistakesGroup1);
        mistakesGroup2 = findViewById(R.id.mistakesGroup2);

    }

    private void setupDiceViews() {
        redDieView = findViewById(R.id.redDieView);
        yellowDieView = findViewById(R.id.yellowDieView);
        greenDieView = findViewById(R.id.greenDieView);
        blueDieView = findViewById(R.id.blueDieView);
        whiteDieView1 = findViewById(R.id.whiteDieView1);
        whiteDieView2 = findViewById(R.id.whiteDieView2);
    }

    private void setupDiceLayout() {
        diceLayout = findViewById(R.id.diceLayout);

        diceLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                rollDice();
            }
        });
    }

    private void setupFinishButton() {
        finishButton = (BootstrapButton) findViewById(R.id.finishBtn);

        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                setupAlertDialog(true);
                showAlertDialog();
            }
        });
    }

    private void setupAlertDialog(final boolean finishGameRequest) {
        dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle("Confirmation");
        dialogBuilder.setMessage("Are you sure?");

        dialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if(finishGameRequest) {
                    calculatePlayersCrosses();
                    calculateResults();

                    // Start QwixxSimpleGameActivity.class
                    Intent myIntent = new Intent(QwixxSimpleGameActivity.this,
                            FinishGameActivity.class);
                    myIntent.putExtra("resultPlayer1", String.valueOf(resultPlayer1));
                    myIntent.putExtra("resultPlayer2", String.valueOf(resultPlayer2));
                    startActivity(myIntent);
                } else {
                   finish();
                }
            }
        });

        dialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
                hideNavigationBar();
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
